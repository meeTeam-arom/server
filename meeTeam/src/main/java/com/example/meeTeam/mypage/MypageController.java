package com.example.meeTeam.mypage;

import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.global.exception.codes.SuccessCode;
import com.example.meeTeam.member.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;

    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    @GetMapping("/profile")
    public BaseResponse<?> getProfile() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Member member = mypageService.getProfile(username);
            return BaseResponse.onSuccess(member);
        } catch (Exception e) {
            return BaseResponse.onFailure("PROFILE_FETCH_ERROR", "Failed to fetch profile information", null);
        }
    }

    @PutMapping("/profile")
    public BaseResponse<?> updateProfile(@RequestParam Double latitude,
                                         @RequestParam Double longitude,
                                         @RequestParam String availableDate) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Member updatedMember = mypageService.updateProfile(username, latitude, longitude, availableDate);
            return BaseResponse.of(SuccessCode.SUCCESS, updatedMember);
        } catch (Exception e) {
            return BaseResponse.onFailure("PROFILE_UPDATE_ERROR", "Failed to update profile information", null);
        }
    }
}
