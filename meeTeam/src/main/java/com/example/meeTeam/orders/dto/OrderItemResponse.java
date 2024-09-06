package com.example.meeTeam.orders.dto;

import com.example.meeTeam.item.Item;
import com.example.meeTeam.orders.OrderItem;
import lombok.Getter;
import lombok.Builder;
import lombok.Setter;

@Getter
@Setter
@Builder

public record OrderItemResponse (
        Long orderItemId,
        Item orderItem,
        Long memberId,
        Long orderId,
        int price
) {
    public static OrderItem OrderItem(Long orderItemId, Item orderItem, Long memberId, Long orderId, int price) {
        return OrderItem.builder()
                .orderItemId(orderItemId).orderItem(orderItem).memberId(memberId).orderId(orderId).price(price)
                .build();
    }

    public static OrderItemResponse fromRequest(OrderItemRequest request) {
        OrderItem orderItem = OrderItem(request.orderItemId(), request.orderItem(), request.memberId(), request.orderId(), request.price());
        return new OrderItemResponse(request.orderItemId(), request.orderItem(), request.memberId(), request.orderId(), request.price());
    }

    public static OrderItemResponse fromEntity(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .orderItemId(orderItem.getOrderItemId())
                .orderItem(orderItem.getOrderItem())
                .memberId(orderItem.getMemberId())
                .orderId(orderItem.getOrderId())
                .build();
    }
}
