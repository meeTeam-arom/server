package com.example.meeTeam.orders.dto;

import com.example.meeTeam.item.Item;
import com.example.meeTeam.orders.OrderItem;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public record OrderItemRequest (
        Long orderItemId,
        Item orderItem,
        Long memberId,
        Long orderId,
        int price
    ) {

    @Builder
    public OrderItemRequest(Long orderItemId, Item orderItem, Long memberId, Long orderId, int price) {
        this.orderItemId = orderItemId;
        this.orderItem = orderItem;
        this.memberId = memberId;
        this.orderId = orderId;
        this.price = price;
    }

    public OrderItem toEntity() {
        return OrderItem.builder()
                .orderItemId(orderItemId)
                .orderItem(orderItem)
                .memberId(memberId)
                .orderId(orderId)
                .price(price)
                .build();
    }
}