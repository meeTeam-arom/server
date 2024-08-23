package com.example.meeTeam.member.repository;

import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.OAuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByMemberEmail(String email);

    Optional<Member> findMemberByMemberEmail(String email);

    Optional<Member> findMemberByMemberName(String username);

    @Query("SELECT m " +
            "FROM Member m " +
            "JOIN m.memberOAuths mo " +
            "ON m.id = mo.member.id " +
            "WHERE mo.oauthId = :oauthId AND mo.oAuthProviderType = :providerType")
    Optional<Member> findMemberByOAuthIdAndProviderType(@Param("oauthId") String oauthId, @Param("providerType") OAuthProviderType providerType);

}
