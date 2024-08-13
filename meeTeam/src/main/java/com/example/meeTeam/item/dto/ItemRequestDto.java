package com.example.meeTeam.item.dto;

public class ItemRequestDto {
    private String item_name;
    private int item_price;
    private String item_introduction;
    private String image_url;

    public ItemRequestDto(String item_name, int item_price, String item_introduction, String image_url) {
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_introduction = item_introduction;
        this.image_url = image_url;
    }


    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public String getItem_introduction() {
        return item_introduction;
    }

    public void setItem_introduction(String item_introduction) {
        this.item_introduction = item_introduction;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}