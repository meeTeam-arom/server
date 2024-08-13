package com.example.meeTeam.item.service;

import com.example.meeTeam.entity.Item;
import com.example.meeTeam.entity.Member;
import com.example.meeTeam.entity.OrderItem;
import com.example.meeTeam.repository.ItemRepository;
import com.example.meeTeam.repository.MemberRepository;
import com.example.meeTeam.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // 아이템 구매
    @Transactional
    public OrderItem purchaseItem(Long memberId, Long itemId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));

        // 사용자 잔액 차감
        member.deductBalance(item.getPrice()); //
        memberRepository.save(member); // 잔액 갱신

        // 구매 아이템 저장
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setMember(member); // Member를 Orders에 설정
        return orderItemRepository.save(orderItem);
    }

    // 아이템 구매 취소
    public void cancelPurchase(Long memberId, Long orderItemId) {

        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Order Item ID"));

        if (!orderItem.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("User does not own this item");
        }

        // 사용자의 잔액 원상복구
        Member member = orderItem.getMember();
        member.setBalance(member.getBalance() + orderItem.getItem().getPrice());
        memberRepository.save(member);

        // 주문했던 상품 삭제
        orderItemRepository.delete(orderItem);
    }
}
