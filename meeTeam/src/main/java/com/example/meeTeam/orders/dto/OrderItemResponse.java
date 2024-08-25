package com.example.meeTeam.orders.dto;

import com.example.meeTeam.item.Item;
import com.example.meeTeam.item.dto.ItemRequest;
import com.example.meeTeam.item.dto.ItemResponse;
import com.example.meeTeam.orders.OrderItem;
import com.example.meeTeam.orders.Orders;
import lombok.Getter;
import lombok.Builder;
import lombok.Setter;

@Getter
@Setter
@Builder

public record OrderItemResponse (
        Long orderItemId,
        Item orderItem,
        Orders order,
        Long memberId,
        Long orderId,
        int price
) {
    public static OrderItem OrderItem(Long orderItemId, Item orderItem, Orders order, Long memberId, Long orderId, int price) {
        return OrderItem.builder()
                .orderItemId(orderItemId).orderItem(orderItem).order(order).memberId(memberId).orderId(orderId).price(price)
                .build();
    }

    public static OrderItemResponse fromRequest(OrderItemRequest request) {
        OrderItem orderItem = OrderItem(request.orderItemId(), request.orderItem(), request.order(), request.memberId(), request.orderId(), request.price());
        return new OrderItemResponse(request.orderItemId(), request.orderItem(), request.order(), request.memberId(), request.orderId(), request.price());
    }

    public static OrderItemResponse fromEntity(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .orderItemId(orderItem.getOrderItemId())
                .orderItem(orderItem.getOrderItem())
                .order(orderItem.getOrder())
                .memberId(orderItem.getMemberId())
                .orderId(orderItem.getOrderId())
                .build();
    }
}
