package com.example.meeTeam.member.controller;

import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.member.dto.MemberDetails;
import com.example.meeTeam.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.example.meeTeam.member.dto.MemberRequest.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public BaseResponse<?> signup(@Valid @RequestBody MemberSignupRequestDto request){
        log.info("컨트롤러 진입");
        return BaseResponse.onSuccess(memberService.createMember(request));
    }

    @PostMapping("/login")
    public BaseResponse<?> login(@Valid @RequestBody MemberLocalLoginRequestDto request, HttpServletResponse response){
        log.info("로그인 진입");
        return BaseResponse.onSuccess(memberService.localLogin(request, response));
    }

    @PostMapping("/kakao-login")
    public BaseResponse<?> kakaoLogin(@Valid @RequestBody MemberKakaoLoginRequestDto request, HttpServletResponse response) throws IOException {
        return BaseResponse.onSuccess(memberService.kakaoLogin(request.userId(), response));
    }
}