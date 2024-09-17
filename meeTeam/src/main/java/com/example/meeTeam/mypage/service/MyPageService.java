package com.example.meeTeam.mypage.service;

import com.example.meeTeam.global.exception.BaseException;
import com.example.meeTeam.global.exception.codes.ErrorCode;
import com.example.meeTeam.member.repository.MemberRepository;
import com.example.meeTeam.mypage.dto.MyPageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.meeTeam.mypage.dto.MyPageResponse.*;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MemberRepository memberRepository;

    public MyPageBriefResponseDto getProfile(String email) {
        return MyPageBriefResponseDto.from(
                        memberRepository.findMemberByMemberEmail(email)
                        .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND))
        );
    }

    public void updateProfile(String email, MyPageRequest.MyPageUpdateRequestDto request) {
        memberRepository.findMemberByMemberEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND))
                .updateMemberMyPage(request);
    }
}
