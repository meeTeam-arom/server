package com.example.meeTeam.evaluation;

import com.example.meeTeam.member.Member;
import com.example.meeTeam.member.dto.MemberDetails;

public interface EvaluationService {
    void updateScore(Member member);

    void doEvaluation(MemberDetails memberDetails, EvaluationDTO.doingEvaluationDTO evaluationDTO);

    void deleteEvaluation(EvaluationDTO evaluationDTO);
}
