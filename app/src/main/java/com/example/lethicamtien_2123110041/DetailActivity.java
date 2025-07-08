package com.example.lethicamtien_2123110041;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView tvProductName, tvProductDesc, tvProductPrice, tvRating;
    Button btnAddToCart, btnBuyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgProduct = findViewById(R.id.imgProduct);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductDesc = findViewById(R.id.tvProductDesc);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvRating = findViewById(R.id.tvRating);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBuyNow = findViewById(R.id.btnBuyNow);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        int imageResId = R.drawable.sofa;

        if (intent != null) {
            String name = intent.getStringExtra("name");
            String description = intent.getStringExtra("description");
            int price = intent.getIntExtra("price", 0);
            imageResId = intent.getIntExtra("image", R.drawable.sofa); // fallback ảnh mặc định

            // Gán dữ liệu
            tvProductName.setText(name);
            tvProductDesc.setText(description);// dùng dữ liệu được truyền vào
            tvProductPrice.setText("Giá: " + price + " VND");
            tvRating.setText("Đánh giá: ★★★★☆");
            imgProduct.setImageResource(imageResId);
        }

        // ✅ Xử lý sự kiện nút Mua Ngay
        int finalImageResId = imageResId; // biến final để truyền vào lambda
        btnBuyNow.setOnClickListener(v -> {
            Intent checkoutIntent = new Intent(DetailActivity.this, CheckoutActivity.class);
            checkoutIntent.putExtra("product_name", tvProductName.getText().toString());
            checkoutIntent.putExtra("product_image", finalImageResId);
            startActivity(checkoutIntent);
        });
    }
}
