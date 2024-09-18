package com.example.meeTeam.item;

import lombok.Getter;

@Getter
public enum ItemCategory {
    HAT("hat"),
    CLOTHES("clothes"),
    SHOES("shoes");

    private String name;

    ItemCategory(String name) {
        this.name = name;
    }
}