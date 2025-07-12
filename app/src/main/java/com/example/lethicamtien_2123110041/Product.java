package com.example.lethicamtien_2123110041;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String desc;
    private int price;
    private String description;
    private String imageUrl;

    public Product(String name, String desc, int price, String description, String imageUrl) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() { return name; }
    public String getDesc() { return desc; }
    public int getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
}
