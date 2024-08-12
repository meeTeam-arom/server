package com.example.meeTeam.member.dto;


import jakarta.validation.constraints.Email;

public record MemberRegisterRequestDto(
        @Email String email,
        String password
) {
}
