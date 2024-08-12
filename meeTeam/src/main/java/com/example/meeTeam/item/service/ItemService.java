package com.example.meeTeam.item.service;

import com.example.meeTeam.item.Item;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    // 상품 등록
    public Item createItem(ItemRequestDto itemRequestDto) {
        Item item = new Item();
        item.setName(itemRequestDto.getItem_name());
        item.setPrice(itemRequestDto.getItem_price());
        item.setDescription(itemRequestDto.getDescription());
        item.setImageUrl(itemRequestDto.getImageUrl());

        return itemRepository.save(item); // Item을 DB에 저장
    }

    // 상품 상세 정보 가져오기
    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + itemId));
    }

    // 아이템 수정
    public Item updateItem(Long id, Item updatedItem) {
        Item item = getItem(id);
        item.setName(updatedItem.getName());
        item.setPrice(updatedItem.getPrice());
        // 추가 수정 더 있으면,,
        return itemRepository.save(item);
    }

    // 아이템 이미지 URL 업데이트
    public void updateItemImage(Long itemId, String imageUrl) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + itemId));

        item.setImageUrl(imageUrl);
        itemRepository.save(item); // 변경사항 -> DB에 저장
    }

    // 상품 삭제
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

}