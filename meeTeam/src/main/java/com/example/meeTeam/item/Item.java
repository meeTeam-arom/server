package com.example.meeTeam.item;


import com.example.meeTeam.global.entity.BaseEntity;
import com.example.meeTeam.image.Image;
import com.example.meeTeam.orders.OrderItem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;
    private String name;
    private String description;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_image_id")
    private Image image;
    private String imageUrl;

    @Builder
    public Item(Long id, int price, String name, String description, Image image, String imageUrl) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.image = image;
        this.imageUrl = imageUrl;
    }
}
