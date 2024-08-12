package com.example.meeTeam.member.controller;

import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.member.dto.MemberDetails;
import com.example.meeTeam.member.dto.MemberRegisterRequestDto;
import com.example.meeTeam.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public BaseResponse<?> signup(@Valid @RequestBody MemberRegisterRequestDto request){
        log.info("컨트롤러 진입");
        return BaseResponse.onSuccess(memberService.createMember(request));
    }

    @PostMapping("/login")
    public BaseResponse<?> login(@Valid @RequestBody MemberRegisterRequestDto request, HttpServletResponse response){
        log.info("로그인 진입");
        return BaseResponse.onSuccess(memberService.localLogin(request, response));
    }


    @GetMapping("/hello")
    public BaseResponse<?> aaa(@AuthenticationPrincipal MemberDetails member){
        System.out.println(member.getUsername());
        return BaseResponse.onSuccess("히히히");
    }

//    @GetMapping("/login/oauth2/code/kakao")
//    public ApiResponse<Object> kakaoRedirect(@RequestParam String code, HttpServletResponse response) {
//        try {
//            memberService.kakaoLogin(code, response);
//        } catch (BaseException e) {
//            return new ApiResponse<>(e.getMessage());
//        }
//        return null;
//    }
}