package com.example.meeTeam.mypage.controller;

import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.member.dto.MemberDetails;
import com.example.meeTeam.mypage.service.MyPageService;
import com.example.meeTeam.mypage.dto.MyPageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    //TODO : 멤버의 구매목록, 채팅방 목록 반환

    private final MyPageService mypageService;

    @GetMapping("/profile")
    public BaseResponse<?> getProfile(@AuthenticationPrincipal MemberDetails principal) {
//        try {
//            Member member = mypageService.getProfile(principal.context().email());
//            return BaseResponse.onSuccess(member);
//        } catch (Exception e) {
//            return BaseResponse.onFailure("PROFILE_FETCH_ERROR", "Failed to fetch profile information", null);
//        }
        return BaseResponse.onSuccess(mypageService.getProfile(principal.context().email()));
    }

    @PutMapping("/profile")
    public BaseResponse<?> updateProfile(@AuthenticationPrincipal MemberDetails principal,
                                         MyPageRequest.MyPageUpdateRequestDto request) {
//        try {
//            mypageService.updateProfile(principal.context().email(), request);
//            return BaseResponse.onSuccess("내 정보를 변경했습니다.");
//        } catch (Exception e) {
//            return BaseResponse.onFailure("PROFILE_UPDATE_ERROR", "Failed to update profile information", null);
//        }
        mypageService.updateProfile(principal.context().email(), request);
        return BaseResponse.onSuccess("내 정보를 변경했습니다.");
    }
}
