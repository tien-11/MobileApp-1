package com.example.lethicamtien_2123110041;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

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
        productList.add(new Product("Rest + Black sọc caro", R.drawable.sp1));
        productList.add(new Product("White + Black caro", R.drawable.sp2));
        productList.add(new Product("Ivory-white + Black", R.drawable.sp3));
        productList.add(new Product("White + Red", R.drawable.sp4));
        productList.add(new Product("White + Black", R.drawable.sp7));
        productList.add(new Product("White + Brown", R.drawable.sp6));
        // Thêm tùy ý

        // Khởi tạo Adapter
        adapter = new ProductAdapter(this, productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
