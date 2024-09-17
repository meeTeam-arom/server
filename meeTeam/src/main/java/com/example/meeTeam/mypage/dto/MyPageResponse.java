package com.example.meeTeam.mypage.dto;

import com.example.meeTeam.member.model.Member;
import lombok.Builder;

public class MyPageResponse {

    @Builder
    public record MyPageBriefResponseDto(
            String memberName,
            String memberPhoneNum,
            String memberEmail,
            double memberMannerTemp,
            Long memberUsedCoins,
            Long memberEarnedCoins,
            double latitude,
            double longitude,
            String availableDate
    ){
        public static MyPageBriefResponseDto from(Member member) {
            return MyPageBriefResponseDto.builder()
                    .memberName(member.getMemberName())
                    .memberPhoneNum(member.getMemberPhoneNum())
                    .memberEmail(member.getMemberEmail())
                    .memberMannerTemp(member.getMemberMannerTemp())
                    .memberUsedCoins(member.getMemberUsedCoins())
                    .memberEarnedCoins(member.getMemberEarnedCoins())
                    .latitude(member.getLatitude())
                    .longitude(member.getLongitude())
                    .availableDate(member.getAvailableDate())
                    .build();
        }
    }

}
