package com.example.meeTeam.member.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class MemberRequest{
    public record MemberLocalLoginRequestDto(
            @Email String email,
            @NotEmpty String password
    ){}

    public record MemberSignupRequestDto(
            @Email String email,
            @NotEmpty String password
    ){}

    public record MemberKakaoLoginRequestDto(
            @NotEmpty String tokenId
    ){}
}