package com.example.lethicamtien_2123110041;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView tvProductName, tvProductDesc, tvProductPrice, tvRating;
    Button btnAddToCart, btnBuyNow;
    String name = "", description = "", imageUrl = "";
    int price = 0;

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

        Intent intent = getIntent();

        if (intent != null) {
            name = intent.getStringExtra("name");
            description = intent.getStringExtra("description");
            price = intent.getIntExtra("price", 0);
            imageUrl = intent.getStringExtra("imageUrl");

            tvProductName.setText(name);
            tvProductDesc.setText(description);
            tvProductPrice.setText("Giá: " + price + " VND");
            tvRating.setText("Đánh giá: ★★★★☆");

            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .into(imgProduct);
        }

        btnAddToCart.setOnClickListener(v -> {
            CartItem item = new CartItem(name, imageUrl, price, 1);
            CartManager.addToCart(item);
            Toast.makeText(DetailActivity.this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
        });

        btnBuyNow.setOnClickListener(v -> {
            Intent checkoutIntent = new Intent(DetailActivity.this, CheckoutActivity.class);
            checkoutIntent.putExtra("product_name", name);
            checkoutIntent.putExtra("product_imageUrl", imageUrl);
            startActivity(checkoutIntent);
        });
    }
}



