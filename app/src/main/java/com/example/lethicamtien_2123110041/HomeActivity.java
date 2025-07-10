package com.example.lethicamtien_2123110041;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> productList;
    ImageView imgCartIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        imgCartIcon = findViewById(R.id.imgCartIcon);
        imgCartIcon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });
        EditText edtSearch = findViewById(R.id.edtSearch);

        edtSearch.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String keyword = edtSearch.getText().toString().trim();
                performSearch(keyword); // Gọi hàm tìm kiếm
                return true;
            }
            return false;
        });


        // Khởi tạo danh sách và Adapter
        productList = new ArrayList<>();
        adapter = new ProductAdapter(this, productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Gọi API
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
                        productList.clear(); // xóa cũ nếu có

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);

                            String name = obj.optString("name");
                            String desc = obj.optString("desc");
                            int price = obj.optInt("price");
                            String description = obj.optString("description");
                            String imageUrl = obj.optString("imageUrl");

                            productList.add(new Product(name, desc, price, description, imageUrl));
                        }

                        adapter.notifyDataSetChanged();

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
        for (Product p : productList) {
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
