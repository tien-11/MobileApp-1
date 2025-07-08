package com.example.lethicamtien_2123110041;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final List<CartItem> cartItems = new ArrayList<>();

    // Thêm sản phẩm vào giỏ (chống trùng tên)
    public static void addToCart(CartItem item) {
        for (CartItem c : cartItems) {
            if (c.getName().equals(item.getName())) {
                c.setQuantity(c.getQuantity() + item.getQuantity());
                return;
            }
        }
        cartItems.add(item);
    }

    public static List<CartItem> getCart() {
        return cartItems;
    }

    public static void clearCart() {
        cartItems.clear();
    }
}
