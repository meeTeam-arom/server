package com.example.meeTeam.item.controller;

import com.example.demo.dto.ItemRequestDto;
import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private ItemService itemService;

    // 상품 등록 폼을 제공
    @GetMapping("/admin/item/new")
    public String showCreateItemForm(Model model) {
        model.addAttribute("itemRequestDto", new ItemRequestDto());
        return "createItem";
    }

    // 상품 등록 요청 처리
    @PostMapping("/admin/item/new")
    public String createItem(@ModelAttribute ItemRequestDto itemRequestDto, Model model) {
        Item newItem = itemService.createItem(itemRequestDto);
        model.addAttribute("item", newItem);
        return "itemDetail";
    }
}