package com.example.meeTeam.global.auth.token;

import com.example.meeTeam.global.auth.token.vo.AccessToken;
import com.example.meeTeam.global.auth.token.vo.RefreshToken;
import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.dto.MemberDTO;

public interface TokenProvider {
    AccessToken generateAccessToken(Member member);
    AccessToken generateAccessToken(MemberDTO memberDTO);

    RefreshToken generateRefreshToken(Member member);
    RefreshToken generateRefreshToken(MemberDTO memberDTO);
}
