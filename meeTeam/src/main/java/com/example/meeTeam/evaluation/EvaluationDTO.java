package com.example.meeTeam.evaluation;

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

    @Getter
    public static class doingEvaluationDTO{
        private List<String> targetMembersEmail=new ArrayList<>();
        private List<Integer> scoreList = new ArrayList<>();
    }


}
