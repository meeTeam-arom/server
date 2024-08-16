package com.example.meeTeam.member.service;

import com.example.meeTeam.global.auth.token.vo.TokenResponse;
import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.OAuthProviderType;
import com.example.meeTeam.member.dto.MemberDTO;
import com.example.meeTeam.member.dto.MemberDetails;
import com.example.meeTeam.member.dto.MemberRegisterRequestDto;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberService {

    MemberDTO createMember(MemberRegisterRequestDto request);
    Member findMemberByOAuthId(String oauthId, OAuthProviderType providerType);
    TokenResponse localLogin(MemberRegisterRequestDto request, HttpServletResponse response);
}
