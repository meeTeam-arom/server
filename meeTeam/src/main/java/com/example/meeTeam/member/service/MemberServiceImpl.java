package com.example.meeTeam.member.service;

import com.example.meeTeam.global.auth.token.JwtProvider;
import com.example.meeTeam.global.auth.token.vo.AccessToken;
import com.example.meeTeam.global.auth.token.vo.RefreshToken;
import com.example.meeTeam.global.auth.token.vo.TokenResponse;
import com.example.meeTeam.global.exception.BaseException;
import com.example.meeTeam.global.exception.codes.ErrorCode;
import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.OAuthProviderType;
import com.example.meeTeam.member.converter.MemberConverter;
import com.example.meeTeam.member.dto.MemberDTO;
import com.example.meeTeam.member.dto.MemberRegisterRequestDto;
import com.example.meeTeam.member.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.meeTeam.global.properties.JwtProperties.*;


@RequiredArgsConstructor
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public MemberDTO createMember(MemberRegisterRequestDto request) {
        if (memberRepository.existsByEmail(request.email())) {
            log.warn("[createMember] email: {}, {}", request.email(), ErrorCode.EXIST_EMAIL);
            throw new BaseException(ErrorCode.EXIST_EMAIL);
        }

        Member member = Member.builder()
                .email(request.email())
                .memberPassword(passwordEncoder.encode(request.password()))
                .build();

        member = memberRepository.save(member);

        return MemberConverter.convert(member);
    }

    @Transactional
    public Member findMemberByOAuthId(String oauthId, OAuthProviderType providerType) {

        return memberRepository.findMemberByOAuthIdAndProviderType(oauthId, providerType)
                .orElseThrow(() -> {
                    log.warn("[findMemberByOAuthId] id:{}, {}", oauthId, ErrorCode.MEMBER_NOT_FOUND);
                    return new BaseException(ErrorCode.MEMBER_NOT_FOUND);
                });
    }

    public TokenResponse localLogin(MemberRegisterRequestDto request, HttpServletResponse response){
        Member member = memberRepository.findMemberByEmail(request.email()).orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(request.password(), member.getMemberPassword())){
            log.info("비밀번호 틀림");
            throw new BaseException(ErrorCode.PASSWORD_ERROR);
        }

        AccessToken accessToken = jwtProvider.generateAccessToken(member);
        RefreshToken refreshToken = jwtProvider.generateRefreshToken(member);
        TokenResponse tokenResponse = TokenResponse.of(accessToken, refreshToken);
        response.addHeader(JWT_ACCESS_TOKEN_HEADER_NAME, JWT_ACCESS_TOKEN_TYPE + accessToken.token());

        Cookie refreshTokenCookie = new Cookie(JWT_REFRESH_TOKEN_COOKIE_NAME, refreshToken.token());
        refreshTokenCookie.setMaxAge((int) REFRESH_TOKEN_EXPIRE_TIME);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);

        return tokenResponse;
    }

    public void kakaoLogin(String code, HttpServletResponse response) {

    }
}
