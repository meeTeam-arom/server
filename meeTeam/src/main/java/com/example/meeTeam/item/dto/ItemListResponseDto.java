package com.example.meeTeam.item.dto;

import com.example.meeTeam.image.Image;
import com.example.meeTeam.item.Item;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record ItemListResponseDto(
        List<ItemResponse> itemResponses
) {

    public static ItemListResponseDto from(Page<Item> items){
        return ItemListResponseDto.builder()
                .itemResponses(
                        items.stream()
                        .map(ItemResponse::fromEntity)
                        .toList())
                .build();
    }
}
