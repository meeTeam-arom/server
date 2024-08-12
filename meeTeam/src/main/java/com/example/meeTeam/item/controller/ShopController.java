package com.example.meeTeam.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    @GetMapping("/shop/home")
    public String shopHome(Model model) {
        // 모델에 데이터를 추가가능
        // 예: model.addAttribute("items", itemService.getAllItems());

        // "home"이라는 이름의 뷰(HTML 파일 등) 반환
        return "shopHome";
    }

    // 상점 홈에서 상품들 중 하나의 상세 페이지로 이동
    @GetMapping("/shop/item/{itemId}")
    public String getItemDetail(@PathVariable Long itemId, Model model) {
        Item item = itemService.getItemById(itemId);
        model.addAttribute("item", item);
        return "itemDetail";
    }
}