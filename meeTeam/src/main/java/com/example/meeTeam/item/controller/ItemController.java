package com.example.meeTeam.item.controller;

import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.item.Item;
import com.example.meeTeam.item.dto.ItemFindRequest;
import com.example.meeTeam.item.dto.ItemRequest;
import com.example.meeTeam.item.dto.ItemResponse;
import com.example.meeTeam.item.dto.ItemUpdateRequest;
import com.example.meeTeam.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public BaseResponse<ItemResponse> createItem(@RequestBody ItemRequest request) {
        ItemResponse createdItemResponse = ItemResponse.fromEntity(
                itemService.createItem(request.id(), request.price(), request.name(), request.description(), request.image(), request.imageUrl()));
        return BaseResponse.onSuccess(createdItemResponse);
    }

    @PostMapping
    public BaseResponse<ItemResponse> getItemDetail(@RequestBody ItemFindRequest request) {
        ItemResponse item = itemService.getItemDetail(request);
        return BaseResponse.onSuccess(item);
    }

    @PutMapping("/{id}")
    public BaseResponse<ItemResponse> updateItem(@PathVariable Long id, @RequestBody ItemUpdateRequest request) {
        Item item = itemService.updateItem(id, request);
        ItemResponse itemUpdated = ItemResponse.fromEntity(item);
        return BaseResponse.onSuccess(itemUpdated);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Long> deleteItem(@PathVariable Long id) {
        Long deletedId = itemService.deleteItem(id);
        return BaseResponse.onSuccess(deletedId);   // 삭제한 상품 id를 넘김
    }
}