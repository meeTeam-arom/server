package com.example.meeTeam.member.controller;

import com.example.meeTeam.global.auth.member.MemberAuthContext;
import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.member.dto.MemberDetails;
import com.example.meeTeam.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.example.meeTeam.member.dto.MemberRequest.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public BaseResponse<?> signup(@Valid @RequestBody MemberSignupRequestDto request){
        return BaseResponse.onSuccess(memberService.createMember(request));
    }

    //이메일 중복 확인
    @GetMapping("/signup/checkEmail")
    public BaseResponse<?> checkId(@RequestParam("email") String email){
        return BaseResponse.onSuccess(memberService.checkDuplicateId(email));
    }

    @PostMapping("/login")
    public BaseResponse<?> login(@Valid @RequestBody MemberLocalLoginRequestDto request, HttpServletResponse response){
        return BaseResponse.onSuccess(memberService.localLogin(request, response));
    }

    @PostMapping("/kakao-login")
    public BaseResponse<?> kakaoLogin(@Valid @RequestBody MemberKakaoLoginRequestDto request, HttpServletResponse response) throws IOException {
        return BaseResponse.onSuccess(memberService.kakaoLogin(request.userId(), response));
    }

    @PostMapping("/user-info")
    public BaseResponse<?> postUserInfoAfterSignUp(@AuthenticationPrincipal MemberAuthContext context,
                                                   @Valid @RequestBody MemberAdditionInfoRequestDto request){
        return BaseResponse.onSuccess(memberService.getMemberAdditionInfo(context,  request));
    }

}