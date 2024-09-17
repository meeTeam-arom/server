package com.example.meeTeam.member.service;

import com.example.meeTeam.global.auth.member.MemberAuthContext;
import com.example.meeTeam.global.auth.token.vo.TokenResponse;
import com.example.meeTeam.member.dto.MemberResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.meeTeam.member.dto.MemberRequest.*;

public interface MemberService {

    String createMember(MemberSignupRequestDto request);
    MemberResponse.MemberTokenResDto localLogin(MemberLocalLoginRequestDto request, HttpServletResponse response);
    MemberResponse.MemberTokenResDto kakaoLogin(Long id, HttpServletResponse response) throws IOException;
    Boolean checkDuplicateId(String id);
    String getMemberAdditionInfo(MemberAuthContext context, MemberAdditionInfoRequestDto request);
}
