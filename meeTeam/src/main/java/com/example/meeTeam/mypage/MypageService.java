package com.example.meeTeam.mypage;

import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MypageService {

    private final MemberRepository memberRepository;

    public MypageService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getProfile(String username) {
        return memberRepository.findMemberByUsername(username)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public Member updateProfile(String username, Double latitude, Double longitude, String availableDate) {
        Member member = memberRepository.findMemberByUsername(username)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        member.setLatitude(latitude);
        member.setLongitude(longitude);
        member.setAvailableDate(availableDate);

        return memberRepository.save(member);
    }
}
