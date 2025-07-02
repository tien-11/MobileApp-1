package com.example.lethicamtien_2123110041;

public class Product {
    private String name;

    private int imageResId;

    private String price;
    private String desc; // mô tả ngắn
    private String description; // mô tả chi tiết

    public Product(String name, String desc, String price, int imageResId, String description) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.imageResId = imageResId;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDesc() { return desc; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public String getDescription() { return description; }
}
