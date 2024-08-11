package com.example.meeTeam.evaluation;

import com.example.meeTeam.member.Member;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {
    private Long id;
    private double evaluationScore;
    private Member member;
}
