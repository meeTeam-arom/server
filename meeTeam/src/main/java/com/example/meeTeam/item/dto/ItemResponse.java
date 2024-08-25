package com.example.meeTeam.item.dto;

import com.example.meeTeam.image.Image;
import com.example.meeTeam.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public record ItemResponse (
        Long itemId, int itemPrice, String itemName,
        String description, Image itemImage, String imageUrl
    ) {
    public static Item item(Long itemId, int itemPrice, String itemName, String description, Image itemImage, String imageUrl) {
        return Item.builder()
                .id(itemId).price(itemPrice)
                .name(itemName).description(description)
                .image(itemImage).imageUrl(imageUrl)
                .build();
    }

    public static ItemResponse fromRequest(ItemRequest request) {
        Item item = item(request.id(), request.price(), request.name(), request.description(), request.image(), request.imageUrl());
        return new ItemResponse(request.id(), request.price(), request.name(), request.description(), request.image(), request.imageUrl());
    }

    public static ItemResponse fromEntity(Item item) {
        return ItemResponse.builder()
                .itemId(item.getId())
                .itemPrice(item.getPrice())
                .itemName(item.getName())
                .description(item.getDescription())
                .itemImage(item.getImage())
                .imageUrl(item.getImageUrl())
                .build();
    }
}