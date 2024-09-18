package com.example.meeTeam.member.dto;


import com.example.meeTeam.member.model.OAuthProviderType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class MemberRequest{
    public record MemberLocalLoginRequestDto(
            @Email String email,
            @NotEmpty String password
    ){}

    public record MemberSignupRequestDto(
            @Email String email,
            @NotEmpty String password,
            @Pattern(regexp = "^010\\d{4}\\d{4}$", message = "핸드폰 번호 형식이 올바르지 않습니다.") String phoneNumber,
            OAuthProviderType loginType
    ){}

    public record MemberKakaoLoginRequestDto(
            @NotEmpty Long userId
    ){}

    public record MemberAdditionInfoRequestDto(
            @NotEmpty String name,
            double latitude,
            double longitude
    ){}

    public record MemberSMSCertificationRequestDto(
            @Pattern(regexp = "^010\\d{4}\\d{4}$", message = "핸드폰 번호 형식이 올바르지 않습니다.") String phoneNumber
    ){}

    public record MemberSMSValidationRequestDto(
            @NotEmpty String code
    ){}
}