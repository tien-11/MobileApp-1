package com.example.lethicamtien_2123110041;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private TextView tvTotalPrice;
    private Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);

        // Tạo adapter với callback cập nhật tổng tiền
        cartAdapter = new CartAdapter(this, CartManager.getCart(), this::updateTotalPrice);
        recyclerView.setAdapter(cartAdapter);
        updateTotalPrice(); // Gọi lần đầu


        // Gọi cập nhật tổng tiền lần đầu
        updateTotalPrice();

        // Nút "Mua ngay"
        btnCheckout.setOnClickListener(v -> {
            int selectedCount = getSelectedCount();
            if (selectedCount > 0) {
                Toast.makeText(this, "Đặt mua " + selectedCount + " sản phẩm", Toast.LENGTH_SHORT).show();
                // TODO: Xử lý đặt hàng
            } else {
                Toast.makeText(this, "Vui lòng chọn sản phẩm để mua!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTotalPrice() {
        int total = 0;
        int selectedCount = 0;
        for (CartItem item : CartManager.getCart()) {
            if (item.isSelected()) {
                total += item.getPrice() * item.getQuantity();
                selectedCount++;
            }
        }
        tvTotalPrice.setText("Tổng tiền: " + total + " VND");
        btnCheckout.setText("Mua (" + selectedCount + ")");
    }


    private int getSelectedCount() {
        int count = 0;
        for (CartItem item : CartManager.getCart()) {
            if (item.isSelected()) {
                count += item.getQuantity();
            }
        }
        return count;
    }
}
