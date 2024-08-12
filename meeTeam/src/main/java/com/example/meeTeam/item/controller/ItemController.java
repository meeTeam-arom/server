package com.example.meeTeam.item.controller;

import com.example.meeTeam.item.Item;
import com.example.meeTeam.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.getItem(id);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    // 아이템 이미지 업데이트 엔드포인트
    @PutMapping("/{itemId}/image")
    public ResponseEntity<Map<String, Object>> updateItemImage(
            @PathVariable Long itemId,
            @RequestBody Map<String, String> request) {

        String imageUrl = request.get("imageUrl");
        itemService.updateItemImage(itemId, imageUrl);

        // 응답 생성
        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("code", 200);
        response.put("message", "요청에 성공하였습니다.");
        response.put("result", "상품 이미지 수정이 완료됐습니다.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}