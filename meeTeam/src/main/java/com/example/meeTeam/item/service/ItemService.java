package com.example.meeTeam.item.service;

import com.example.meeTeam.global.exception.BaseException;
import com.example.meeTeam.global.exception.codes.ErrorCode;
import com.example.meeTeam.image.Image;
import com.example.meeTeam.item.Item;
import com.example.meeTeam.item.dto.*;
import com.example.meeTeam.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemListResponseDto getAllItemsByCategory(String category, Pageable pageable){
        Page<Item> items = itemRepository.findByItemCategoryWithPaging(category, pageable);
        return ItemListResponseDto.from(items);
    }

    // 새 상품 등록
    public Item createItem(Long id, int price, String name, String description, Image image, String imageUrl) {
        itemRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.ITEM_NOT_FOUND));

        ItemRequest newItem = new ItemRequest(id, price, name, description, image, imageUrl);
        Item item = newItem.toEntity();

        itemRepository.save(item);
        return item;
    }

    // 상품 상세 정보 가져오기
    @Transactional(readOnly = true)
    public ItemResponse getItemDetail(ItemFindRequest request) {
        itemRepository.findById(request.getId())
                .orElseThrow(() -> new BaseException(ErrorCode.ITEM_NOT_FOUND));

        Optional<Item> foundItem = itemRepository.findById(request.getId());
        ItemResponse item = new ItemResponse(foundItem.get().getId(), foundItem.get().getPrice(), foundItem.get().getName(),
                foundItem.get().getDescription(), foundItem.get().getImage(), foundItem.get().getImageUrl());

        return item;
    }

    // 상품 정보 수정
    public Item updateItem(Long ID, ItemUpdateRequest request) {

        Item itemFound = itemRepository.findById(ID)
                .orElseThrow(() -> new BaseException(ErrorCode.ITEM_NOT_FOUND));

        Long id = request.getId() != null ? request.getId() : itemFound.getId();
        ItemRequest newItem = updateValuesWith(request, itemFound, id);
        Item item = newItem.toEntity();

        itemRepository.save(item);
        return item;
    }
    private ItemRequest updateValuesWith(ItemUpdateRequest request, Item itemFound, Long id) {
        int price = request.getPrice() != 0 ? request.getPrice() : itemFound.getPrice();
        String name = request.getName() != null ? request.getName() : itemFound.getName();
        String description = request.getDescription() != null ? request.getDescription() : itemFound.getDescription();
        Image image = request.getImage() != null ? request.getImage() : itemFound.getImage();
        String imageUrl = request.getImageUrl() != null ? request.getImageUrl() : itemFound.getImageUrl();

        return new ItemRequest(id, price, name, description, image, imageUrl);
    }

    // 상품 삭제
    public Long deleteItem(Long id) {
        itemRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.ITEM_NOT_FOUND));

        itemRepository.deleteById(id);
        return id;
    }
}