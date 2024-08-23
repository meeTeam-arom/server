package com.example.meeTeam.member.service;

import com.example.meeTeam.global.auth.member.MemberAuthContext;
import com.example.meeTeam.global.exception.BaseException;
import com.example.meeTeam.global.exception.codes.ErrorCode;
import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.dto.MemberDetails;
import com.example.meeTeam.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public MemberDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByEmail(username)
                .orElseThrow(() -> {
                    log.info("[loadUserByUsername] username:{}, {}", username, ErrorCode.MEMBER_NOT_FOUND);
                    return new BaseException(ErrorCode.MEMBER_NOT_FOUND);
                });
        MemberAuthContext ctx = MemberAuthContext.of(member);
        return new MemberDetails(ctx);
    }
}