package com.example.meeTeam.member.dto;


import com.example.meeTeam.member.Member;

public record MemberDTO(
        Long id,
        String email
) {
    public static MemberDTO from(Member member) {
        return new MemberDTO(member.getId(), member.getEmail());
    }
}
