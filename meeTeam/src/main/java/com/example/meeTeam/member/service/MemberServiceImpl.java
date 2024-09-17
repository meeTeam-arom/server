package com.example.meeTeam.member.service;

import com.example.meeTeam.global.auth.member.MemberAuthContext;
import com.example.meeTeam.global.auth.token.JwtProvider;
import com.example.meeTeam.global.auth.token.vo.AccessToken;
import com.example.meeTeam.global.auth.token.vo.RefreshToken;
import com.example.meeTeam.global.auth.token.vo.TokenResponse;
import com.example.meeTeam.global.exception.BaseException;
import com.example.meeTeam.member.model.Member;
import com.example.meeTeam.member.model.MemberOAuth;
import com.example.meeTeam.member.dto.MemberResponse;
import com.example.meeTeam.member.repository.MemberOAuthRepository;
import com.example.meeTeam.member.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Optional;

import static com.example.meeTeam.global.exception.codes.ErrorCode.*;
import static com.example.meeTeam.global.properties.JwtProperties.*;
import static com.example.meeTeam.member.dto.MemberRequest.*;


@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberOAuthRepository memberOAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public String createMember(MemberSignupRequestDto request) {

        Member member = Member.createMember(request);
        member.encodePassword(passwordEncoder.encode(request.password()));
        memberRepository.save(member);

        MemberOAuth memberOAuth = MemberOAuth.createMemberOAuth(request.loginType());
        memberOAuth.updateMemberOAuthBy(member);
        memberOAuthRepository.save(memberOAuth);
        return "회원가입이 완료됐습니다.";
    }

    @Transactional
    public MemberResponse.MemberTokenResDto localLogin(MemberLocalLoginRequestDto request, HttpServletResponse response){

        Member member = memberRepository
                .findMemberByMemberEmail(request.email())
                .orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(request.password(), member.getMemberPassword())){
            log.error("비밀번호 틀림");
            throw new BaseException(PASSWORD_ERROR);
        }

        return MemberResponse.MemberTokenResDto.from(getTokenResponse(response, member));
    }

    @Transactional
    public MemberResponse.MemberTokenResDto kakaoLogin(Long id, HttpServletResponse response) throws IOException{

        Optional<MemberOAuth> optionalMemberOAuth = memberOAuthRepository.findById(id);
        if (optionalMemberOAuth.isEmpty()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            MultiValueMap<String, String> responseBody = new LinkedMultiValueMap<>();
            responseBody.add("id", id.toString());
            response.getWriter().write(responseBody.toString());
            response.sendRedirect("/signup");
        }

        Member member = optionalMemberOAuth.get().getMember();
        return MemberResponse.MemberTokenResDto.from(getTokenResponse(response, member));
    }

    public Boolean checkDuplicateId(String email) {

        if(memberRepository.existsByMemberEmail(email))
            throw new BaseException(EXIST_EMAIL);
        return true;
    }

    public String getMemberAdditionInfo(MemberAuthContext context, MemberAdditionInfoRequestDto request) {

        Member member = memberRepository.findMemberByMemberEmail(context.email())
                .orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));
        member.addMemberAdditionInfo(request);
        return "설정 완료";
    }

    @NotNull
    private TokenResponse getTokenResponse(HttpServletResponse response, Member member) {

        AccessToken accessToken = jwtProvider.generateAccessToken(member);
        RefreshToken refreshToken = jwtProvider.generateRefreshToken(member);
        TokenResponse tokenResponse = TokenResponse.of(accessToken, refreshToken);
        response.addHeader(JWT_ACCESS_TOKEN_HEADER_NAME, JWT_ACCESS_TOKEN_TYPE + accessToken.token());

        Cookie refreshTokenCookie = new Cookie(JWT_REFRESH_TOKEN_COOKIE_NAME, refreshToken.token());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setMaxAge((int) REFRESH_TOKEN_EXPIRE_TIME);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);

        return tokenResponse;
    }
}
