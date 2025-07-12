package com.example.lethicamtien_2123110041;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String name;
    private String imageUrl;
    private int price;
    private int quantity;
    private boolean selected;

    public CartItem(String name, String imageUrl, int price, int quantity) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.selected = false;
    }

    public String getName() { return name; }
    public String getImageUrl() { return imageUrl; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public boolean isSelected() { return selected; }

    public void setName(String name) { this.name = name; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setPrice(int price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setSelected(boolean selected) { this.selected = selected; }
}
