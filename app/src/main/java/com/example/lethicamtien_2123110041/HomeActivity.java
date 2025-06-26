package com.example.lethicamtien_2123110041;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);

        // Dữ liệu mẫu
        productList = new ArrayList<>();
        productList.add(new Product("Ghế Sofa", R.drawable.sofa));
        productList.add(new Product("Tủ 2 Ngăn", R.drawable.tu2ngan));
        productList.add(new Product("Bộ bàn ghế cao cấp", R.drawable.banghe));
        productList.add(new Product("Đèn Ngủ", R.drawable.denngu));
        productList.add(new Product("Kệ Sách", R.drawable.kesach));
        productList.add(new Product("Bộ nệm cao cấp", R.drawable.nem));
        // Thêm tùy ý

        // Khởi tạo Adapter
        adapter = new ProductAdapter(this, productList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }
}
