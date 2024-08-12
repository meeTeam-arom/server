package com.example.meeTeam.global.auth.handler;

import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.global.exception.codes.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuthFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        BaseResponse<Object> basicResponse = BaseResponse
                .onFailure(ErrorCode.INVALID_EMAIL_OR_PASSWORD.getCode(), ErrorCode.INVALID_EMAIL_OR_PASSWORD.getMessage(), null);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(basicResponse));
    }
}