package com.example.meeTeam.member.dto;

import com.example.meeTeam.global.auth.token.vo.TokenResponse;

public class MemberResponse {
    public record MemberKakaoSignUpResDto(
            String id,
            String name
    ){}

    public record MemberTokenResDto(
            TokenResponse tokenResponse
    ){
        public static MemberTokenResDto from(TokenResponse tokenResponse) {
            return new MemberTokenResDto(tokenResponse);
        }
    }
}
