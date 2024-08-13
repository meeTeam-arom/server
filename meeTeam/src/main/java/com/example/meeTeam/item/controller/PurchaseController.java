package com.example.meeTeam.item.controller;

import com.example.meeTeam.entity.OrderItem;
import com.example.meeTeam.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/{memberId}/{itemId}")
    public OrderItem purchaseItem(@PathVariable Long memberId, @PathVariable Long itemId) {
        return purchaseService.purchaseItem(memberId, itemId);
    }
}
