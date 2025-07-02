package com.example.lethicamtien_2123110041;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lethicamtien_2123110041.CartAdapter;
import com.example.lethicamtien_2123110041.CartManager;

import java.util.List;
public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartAdapter = new CartAdapter(this, CartManager.cartItems);
        recyclerView.setAdapter(cartAdapter);
    }
}
