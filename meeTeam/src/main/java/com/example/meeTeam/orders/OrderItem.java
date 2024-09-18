package com.example.meeTeam.orders;

import com.example.meeTeam.global.entity.BaseEntity;
import com.example.meeTeam.item.Item;
import com.example.meeTeam.member.model.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private Long memberId;
    private Long orderId;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_order_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_orderitem_id")
    private Member member;

    @Builder
    public OrderItem(Long orderItemId, Item orderItem, Long memberId, Long orderId, int price) {
        this.orderItemId = orderItemId;
        this.item = orderItem;
        this.memberId = memberId;
        this.orderId = orderId;
        this.price = price;
    }
}