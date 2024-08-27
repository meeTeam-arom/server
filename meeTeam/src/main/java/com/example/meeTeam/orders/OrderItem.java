package com.example.meeTeam.orders;

import com.example.meeTeam.global.entity.BaseEntity;
import com.example.meeTeam.item.Item;
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
    @JoinColumn(name = "item_id")
    private Item orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Orders order;

    @Builder
    public OrderItem(Long orderItemId, Item orderItem, Orders order, Long memberId, Long orderId, int price) {
        this.orderItemId = orderItemId;
        this.orderItem = orderItem;
        this.order = order;
        this.memberId = memberId;
        this.orderId = orderId;
        this.price = price;

    }
}