package com.example.meeTeam.item.dto;

import com.example.meeTeam.image.Image;
import com.example.meeTeam.item.Item;
import lombok.Builder;
import lombok.Getter;


public record ItemRequest(
        Long id,
        int price,
        String name,
        String description,
        Image image,
        String imageUrl
) {
    @Builder
    public ItemRequest(Long id, int price, String name, String description, Image image, String imageUrl) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.image = image;
        this.imageUrl = imageUrl;
    }

    public Item toEntity() {
        return Item.builder()
                .id(id)
                .price(price)
                .name(name)
                .description(description)
                .image(image)
                .imageUrl(imageUrl)
                .build();
    }
}