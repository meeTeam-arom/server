package com.example.meeTeam.orders.service;

import com.example.meeTeam.global.exception.BaseException;
import com.example.meeTeam.global.exception.codes.ErrorCode;
import com.example.meeTeam.item.Item;
import com.example.meeTeam.item.repository.ItemRepository;
import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.repository.MemberRepository;
import com.example.meeTeam.orders.OrderItem;
import com.example.meeTeam.orders.dto.OrderItemRequest;
import com.example.meeTeam.orders.dto.OrderItemResponse;
import com.example.meeTeam.orders.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;

    private final OrderItemRepository orderItemRepository;

    // 상품 구매
    @Transactional
    public OrderItem purchaseItem(Long orderItemId, Long memberId, Long itemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new BaseException(ErrorCode.IO_ERROR));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.IO_ERROR));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new BaseException(ErrorCode.IO_ERROR));

        // 회원의 잔액 갱신
        member.setMemberEarnedCoins(member.getMemberEarnedCoins() - item.getPrice());
        member.setMemberUsedCoins(member.getMemberUsedCoins() + item.getPrice());
        orderItemRepository.save(orderItem);
        memberRepository.save(member);
        itemRepository.save(item);

        // 구매 아이템 저장
        OrderItemRequest orderItemRequest = new OrderItemRequest(orderItem.getOrderItemId(), orderItem.getItem(),
                orderItem.getMemberId(), orderItem.getOrderId(), orderItem.getPrice()); //

        return orderItemRepository.save(orderItem);
    }

    // 상품 구매 취소
    public OrderItemResponse cancelPurchase(Long memberId, Long orderItemId) {

        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new BaseException(ErrorCode._BAD_REQUEST));

        if (!orderItem.getMemberId().equals(memberId)) {
            throw new BaseException(ErrorCode.REQUEST_BODY_MISSING_ERROR);
            // 해당 member 가 구매 취소하고자 하는 item 을 구매한 적 없음
        }

        // 사용자의 잔액 원상복구
        Optional<Member> mb = memberRepository.findById(memberId);

        if (mb.isEmpty()) {
            throw new BaseException(ErrorCode.IO_ERROR);
        }
        Member member = mb.get();
        member.setMemberEarnedCoins(member.getMemberEarnedCoins() + orderItem.getPrice());

        memberRepository.save(member);
        orderItemRepository.delete(orderItem);

        return OrderItemResponse.fromEntity(orderItem);
    }
}
