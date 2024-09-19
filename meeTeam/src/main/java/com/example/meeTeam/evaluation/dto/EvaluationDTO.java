package com.example.meeTeam.evaluation.dto;

import com.example.meeTeam.member.model.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {
    private Long id;
    private double evaluationScore;
    private Member member;
    private Member targetMember;
    private boolean complete;

}
