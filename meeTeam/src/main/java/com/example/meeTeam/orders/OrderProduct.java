package com.example.meeTeam.orders;


import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.global.entity.BaseEntity;
import com.example.meeTeam.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OrderProduct")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class OrderProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prouct_id")
    private Products product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Orders order;
}
