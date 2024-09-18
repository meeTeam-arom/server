package com.example.meeTeam.item.controller;

import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.item.Item;
import com.example.meeTeam.item.dto.ItemFindRequest;
import com.example.meeTeam.item.dto.ItemRequest;
import com.example.meeTeam.item.dto.ItemResponse;
import com.example.meeTeam.item.dto.ItemUpdateRequest;
import com.example.meeTeam.item.service.ItemService;
import com.example.meeTeam.member.dto.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/home")
    public BaseResponse<?> storeHome(@AuthenticationPrincipal MemberDetails principal,
                                     @RequestParam(defaultValue = "hat") String category,
                                     @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable){

        return BaseResponse.onSuccess(itemService.getAllItemsByCategory(category, pageable));
    }

    @PostMapping
    public BaseResponse<ItemResponse> createItem(@AuthenticationPrincipal MemberDetails principal,
                                                 @RequestBody ItemRequest request) {
        ItemResponse createdItemResponse = ItemResponse.fromEntity(
                itemService.createItem(request.id(), request.price(), request.name(), request.description(), request.image(), request.imageUrl()));
        return BaseResponse.onSuccess(createdItemResponse);
    }

    @GetMapping
    public BaseResponse<ItemResponse> getItemDetail(@AuthenticationPrincipal MemberDetails principal,
                                                    @RequestBody ItemFindRequest request) {
        ItemResponse item = itemService.getItemDetail(request);
        return BaseResponse.onSuccess(item);
    }

    @PutMapping("/{id}")
    public BaseResponse<ItemResponse> updateItem(@AuthenticationPrincipal MemberDetails principal,
                                                 @PathVariable Long id,
                                                 @RequestBody ItemUpdateRequest request) {
        Item item = itemService.updateItem(id, request);
        ItemResponse itemUpdated = ItemResponse.fromEntity(item);
        return BaseResponse.onSuccess(itemUpdated);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Long> deleteItem(@AuthenticationPrincipal MemberDetails principal,
                                         @PathVariable Long id) {
        Long deletedId = itemService.deleteItem(id);
        return BaseResponse.onSuccess(deletedId);   // 삭제한 상품 id를 넘김
    }
}