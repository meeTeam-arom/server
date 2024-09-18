package com.example.meeTeam.member.controller;

import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.member.validator.SignUpFormValidator;
import com.example.meeTeam.member.dto.MemberDetails;
import com.example.meeTeam.member.service.CoolSmsService;
import com.example.meeTeam.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.example.meeTeam.member.dto.MemberRequest.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final CoolSmsService coolSmsService;
    private final SignUpFormValidator signUpFormValidator;

    @InitBinder("MemberSignupRequestDto")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @PostMapping("/signup")
    public BaseResponse<?> signup(@Valid @RequestBody MemberSignupRequestDto request, HttpSession session){
        session.invalidate();
        return BaseResponse.onSuccess(memberService.createMember(request));
    }

    //이메일 중복 확인
    @GetMapping("/signup/checkEmail")
    public BaseResponse<?> checkId(@RequestParam("email") String email){
        return BaseResponse.onSuccess(memberService.checkDuplicateId(email));
    }

    @PostMapping("/signup/sms")
    public BaseResponse<?> messageCertification(@Valid @RequestBody MemberSMSCertificationRequestDto request, HttpSession session){
        return BaseResponse.onSuccess(coolSmsService.sendSms(request.phoneNumber(), session));
    }

    @GetMapping("/signup/sms/validate")
    public BaseResponse<?> messageValidation(@Valid @RequestBody MemberSMSValidationRequestDto request, HttpSession session){
        return BaseResponse.onSuccess(coolSmsService.validateSMS(request.code(), session));
    }

    @PostMapping("/login/local")
    public BaseResponse<?> login(@Valid @RequestBody MemberLocalLoginRequestDto request, HttpServletResponse response){
        return BaseResponse.onSuccess(memberService.localLogin(request, response));
    }

    @PostMapping("/login/kakao")
    public BaseResponse<?> kakaoLogin(@Valid @RequestBody MemberKakaoLoginRequestDto request, HttpServletResponse response) throws IOException {
        return BaseResponse.onSuccess(memberService.kakaoLogin(request.userId(), response));
    }

    @PostMapping("/user-info")
    public BaseResponse<?> postUserInfoAfterSignUp(@AuthenticationPrincipal MemberDetails principal,
                                                   @Valid @RequestBody MemberAdditionInfoRequestDto request){
        return BaseResponse.onSuccess(memberService.getMemberAdditionInfo(principal.context(),  request));
    }

}