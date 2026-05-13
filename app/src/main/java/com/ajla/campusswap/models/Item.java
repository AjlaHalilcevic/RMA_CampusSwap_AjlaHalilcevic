package com.ajla.campusswap.models;

public class Item {
    private String id;
    private String title;
    private String description;
    private String price;
    private String imageUrl;
    private String ownerId;

    public Item(String id, String title, String description, String price, String imageUrl, String ownerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.ownerId = ownerId;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
    public String getOwnerId() { return ownerId; }
}
