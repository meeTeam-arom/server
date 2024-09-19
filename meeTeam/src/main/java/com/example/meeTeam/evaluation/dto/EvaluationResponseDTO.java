package com.example.meeTeam.evaluation.dto;

import com.example.meeTeam.member.model.Member;

public class EvaluationResponseDTO {
    public record beforeEvaluation(
        Member member,
        Member targetMember
    ){}
}
