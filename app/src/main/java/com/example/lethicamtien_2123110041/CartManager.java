package com.example.lethicamtien_2123110041;
import com.example.lethicamtien_2123110041.CartItem;
import java.util.ArrayList;
import java.util.List;
public class CartManager {
    public static List<CartItem> cartItems = new ArrayList<>();

    public static void addToCart(CartItem item) {
        cartItems.add(item);
    }

    public static void clearCart() {
        cartItems.clear();
    }
}
