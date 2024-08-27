package com.example.meeTeam.item.dto;

import com.example.meeTeam.image.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemUpdateRequest {

    private Long id;
    private int price;
    private String name;
    private String description;
    private Image image;
    private String imageUrl;

    @Builder
    public ItemUpdateRequest(Long id, int price, String name, String description, Image image, String imageUrl) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.image = image;
        this.imageUrl = imageUrl;
    }
}