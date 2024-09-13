package com.example.meeTeam.member;

import lombok.Getter;

@Getter
public enum OAuthProviderType {
    LOCAL("local"),
    KAKAO("kakao"),
    NAVER("naver");

    private final String provider;

    OAuthProviderType(String provider) {
        this.provider = provider;
    }
}