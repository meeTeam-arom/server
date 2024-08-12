package com.example.meeTeam.member.converter;


import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.dto.MemberDTO;

public class MemberConverter {
    public static MemberDTO convert(Member member) {
        return new MemberDTO(member.getId(), member.getEmail());
    }
}
