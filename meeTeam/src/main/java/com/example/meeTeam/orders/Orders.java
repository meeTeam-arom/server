package com.example.meeTeam.orders;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name ="member",nullable = false)
    private Long memberId;

    private String orderTime;
    private boolean isUse;
    private boolean isRefund;
    private int afterOrderCoins;
}
