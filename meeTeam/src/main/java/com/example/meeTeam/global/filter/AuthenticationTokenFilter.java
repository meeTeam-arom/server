package com.example.meeTeam.global.filter;

import com.example.meeTeam.global.auth.token.JwtProvider;
import com.example.meeTeam.global.exception.BaseException;
import com.example.meeTeam.global.exception.codes.ErrorCode;
import com.example.meeTeam.member.dto.MemberDetails;
import com.example.meeTeam.member.service.MemberDetailsService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final MemberDetailsService memberDetailsService;
    private final SecurityContextRepository securityContextRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info(request.getRequestURI());

        String jwtHeader = request.getHeader("Authorization");
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("멤버 인증 시작!!");

        String token = resolveToken(request);

        String aud = jwtProvider.parseAudience(token); // 토큰 Aud에 Member email을 기록하고 있음

        MemberDetails userDetails = memberDetailsService.loadUserByUsername(aud); // memberId를 기반으로 조회

        Authentication authentication
                = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());


        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, request, response);

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        if (authorization == null) {
            throw new BaseException(ErrorCode.EMPTY_TOKEN_PROVIDED);
        }
        if (authorization.startsWith("Bearer ")) { // Bearer 기반 토큰 인증을 함
            return authorization.substring(7);
        }

        throw new BaseException(ErrorCode.EMPTY_TOKEN_PROVIDED);
    }
}
