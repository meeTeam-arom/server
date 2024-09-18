package com.example.meeTeam.member.repository;

import com.example.meeTeam.member.model.MemberOAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberOAuthRepository extends JpaRepository<MemberOAuth, Long> {
    Optional<MemberOAuth> findById(long id);
}
