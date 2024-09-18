package com.example.meeTeam.global.auth.token;

import com.example.meeTeam.global.auth.token.vo.AccessToken;
import com.example.meeTeam.global.auth.token.vo.RefreshToken;
import com.example.meeTeam.member.model.Member;

public interface TokenProvider {
    AccessToken generateAccessToken(Member member);

    RefreshToken generateRefreshToken(Member member);
}
