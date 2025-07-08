package com.example.lethicamtien_2123110041;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart); // Bạn cần đảm bảo layout này có recyclerCart

        recyclerView = findViewById(R.id.recyclerCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Kiểm tra giỏ hàng rỗng
        if (CartManager.getCart().isEmpty()) {
            // Hiển thị thông báo giỏ hàng trống nếu có TextView riêng
            // Ví dụ: emptyTextView.setVisibility(View.VISIBLE);
        } else {
            cartAdapter = new CartAdapter(this, CartManager.getCart());
            recyclerView.setAdapter(cartAdapter);
        }
    }
}
