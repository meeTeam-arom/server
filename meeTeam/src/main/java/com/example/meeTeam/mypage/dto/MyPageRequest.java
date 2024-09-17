package com.example.meeTeam.mypage.dto;

public class MyPageRequest {

    public record MyPageUpdateRequestDto(
            String memberName,
            String memberPhoneNum,
            String memberEmail,
            double latitude,
            double longitude,
            String availableDate
    ){}

}
