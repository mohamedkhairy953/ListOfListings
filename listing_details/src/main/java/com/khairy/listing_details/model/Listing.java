package com.khairy.listing_details.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Listing {
    @SerializedName("name")
   private String name;
    @SerializedName("price")
    private   String price;
    @SerializedName("imageUrls")
    private   ArrayList<String> imageUrls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
