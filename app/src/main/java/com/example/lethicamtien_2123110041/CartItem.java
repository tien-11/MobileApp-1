package com.example.lethicamtien_2123110041;

public class CartItem {
    private String name;
    private int imageResId;
    private String price;
    private int quantity;
    private boolean selected = false;

    public CartItem(String name, int imageResId, String price, int quantity) {
        this.name = name;
        this.imageResId = imageResId;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter và Setter
    public String getName() { return name; }
    public int getImageResId() { return imageResId; }
    public String getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
}
