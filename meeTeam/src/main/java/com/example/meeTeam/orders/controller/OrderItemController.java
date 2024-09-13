package com.example.meeTeam.orders.controller;

import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.item.dto.ItemRequest;
import com.example.meeTeam.item.dto.ItemResponse;
import com.example.meeTeam.orders.service.OrderItemService;
import com.example.meeTeam.orders.OrderItem;
import com.example.meeTeam.orders.dto.OrderItemRequest;
import com.example.meeTeam.orders.dto.OrderItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderItems")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService OrderItemService;

    @PostMapping
    public BaseResponse<OrderItemResponse> purchaseItem(@RequestBody OrderItemRequest request) {
        OrderItemResponse orderItemResponse = OrderItemResponse.fromEntity
                (OrderItemService.purchaseItem(request.orderItemId(), request.memberId(), request.orderId()));
        return BaseResponse.onSuccess(orderItemResponse);
    }

    @DeleteMapping
    public BaseResponse<OrderItemResponse> cancelPurchase(@RequestBody OrderItemRequest request) {
        OrderItemResponse orderItemResponse = OrderItemService.cancelPurchase(request.memberId(),request.orderItemId());
        return BaseResponse.onSuccess(orderItemResponse);
    }

}
