package com.example.meeTeam.evaluation;

import com.example.meeTeam.member.model.Member;
import com.example.meeTeam.member.dto.MemberDetails;

public interface EvaluationService {
    void updateScore(Member member);

    void doEvaluation(MemberDetails memberDetails, EvaluationDTO.doingEvaluationDTO evaluationDTO);
    void beforeDoingEvaluation(Member member, Member targetMember);
    void deleteEvaluation(EvaluationDTO evaluationDTO);
}
