package com.example.meeTeam.global.filter;

import com.example.meeTeam.global.auth.token.JwtProvider;
import com.example.meeTeam.global.exception.BaseException;
import com.example.meeTeam.global.exception.codes.ErrorCode;
import com.example.meeTeam.member.dto.MemberDetails;
import com.example.meeTeam.member.service.MemberDetailsService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

//    @Value("${host.develop.api.ant-match.uri}")
    private List<String> antMatchURIs = new ArrayList<>();

    @PostConstruct
    public void init(){
        antMatchURIs.add("/signup");
        antMatchURIs.add("/login");
        antMatchURIs.add("/login/oauth2/code/naver");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 토큰 검증을 하지 않아야 하는 Path에 대한 예외 처리
        String path = request.getRequestURI();
        log.info("path : {}", path);
        for (String antMatchURI : antMatchURIs) {
            if (path.startsWith(antMatchURI)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String token = this.resolveToken(request);

        String aud = jwtProvider.parseAudience(token); // 토큰 Aud에 Member email을 기록하고 있음

        MemberDetails userDetails = memberDetailsService.loadUserByUsername(aud); // memberId를 기반으로 조회

        Authentication authentication
                = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());


        SecurityContextHolder.getContext().setAuthentication(authentication);

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
