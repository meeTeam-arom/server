package com.example.meeTeam.member.validator;

import com.example.meeTeam.member.dto.MemberRequest;
import com.example.meeTeam.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator  implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(MemberRequest.MemberSignupRequestDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberRequest.MemberSignupRequestDto request = (MemberRequest.MemberSignupRequestDto) target;
        if (memberRepository.existsByMemberEmail(request.email())) {
            errors.rejectValue("email", "EXIST_EMAIL", "이미 존재하는 이메일입니다.");
        }
    }
}
