package com.example.lethicamtien_2123110041;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> productList;       // danh sách đang hiển thị
    List<Product> allProducts;       // danh sách gốc để tìm kiếm
    SuggestionAdapter suggestionAdapter;

    AutoCompleteTextView edtSearch;
    ImageView imgCartIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        edtSearch = findViewById(R.id.edtSearch);
        imgCartIcon = findViewById(R.id.imgCartIcon);

        productList = new ArrayList<>();
        allProducts = new ArrayList<>();

        adapter = new ProductAdapter(this, productList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        suggestionAdapter = new SuggestionAdapter(this, allProducts);
        edtSearch.setAdapter(suggestionAdapter);
        edtSearch.setThreshold(1); // gợi ý từ ký tự đầu tiên

        imgCartIcon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });

        edtSearch.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedProduct = suggestionAdapter.getItem(position);
            if (selectedProduct != null) {
                List<Product> selectedList = new ArrayList<>();
                selectedList.add(selectedProduct);
                adapter.updateData(selectedList);
            }
        });

        edtSearch.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String keyword = edtSearch.getText().toString().trim();
                performSearch(keyword);
                return true;
            }
            return false;
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    adapter.updateData(allProducts); // reset về ban đầu
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        getProductsFromApi();
    }

    private void getProductsFromApi() {
        String url = "https://686c9ae314219674dcc87c1a.mockapi.io/products";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        productList.clear();
                        allProducts.clear();

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);

                            String name = obj.optString("name");
                            String desc = obj.optString("desc");
                            int price = obj.optInt("price");
                            String description = obj.optString("description");
                            String imageUrl = obj.optString("imageUrl");

                            Product product = new Product(name, desc, price, description, imageUrl);
                            productList.add(product);
                            allProducts.add(product);
                        }

                        adapter.notifyDataSetChanged();
                        suggestionAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Lỗi xử lý JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Không kết nối được MockAPI", Toast.LENGTH_SHORT).show();
                }
        );

        queue.add(request);
    }

    private void performSearch(String keyword) {
        List<Product> filteredList = new ArrayList<>();
        for (Product p : allProducts) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(p);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Không tìm thấy sản phẩm", Toast.LENGTH_SHORT).show();
        }

        adapter.updateData(filteredList);
    }
}
