package com.example.lethicamtien_2123110041;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    EditText edtQuantity;
    Button btnOrder;
    TextView txtProductName;
    ImageView imgProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout); // Giao diện thanh toán

        // Ánh xạ
        edtQuantity = findViewById(R.id.edtQuantity);
        btnOrder = findViewById(R.id.btnOrder);
        txtProductName = findViewById(R.id.txtProductName);
        imgProduct = findViewById(R.id.imgProduct);

        // Nhận dữ liệu từ Intent
        String name = getIntent().getStringExtra("product_name");
        int imgResId = getIntent().getIntExtra("product_image", R.drawable.ic_launcher_background);

        txtProductName.setText(name);
        imgProduct.setImageResource(imgResId);

        // Bắt sự kiện đặt hàng
        btnOrder.setOnClickListener(v -> {
            String quantityStr = edtQuantity.getText().toString().trim();
            if (quantityStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập số lượng!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
