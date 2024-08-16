package com.example.meeTeam.member.service;

import com.example.meeTeam.global.auth.token.vo.TokenResponse;
import com.example.meeTeam.member.dto.MemberDTO;
import jakarta.servlet.http.HttpServletResponse;

import static com.example.meeTeam.member.dto.MemberRequest.*;

public interface MemberService {

    MemberDTO createMember(MemberSignupRequestDto request);
    TokenResponse localLogin(MemberLocalLoginRequestDto request, HttpServletResponse response);
}
