package com.example.meeTeam.evaluation.service;

import com.example.meeTeam.evaluation.Evaluation;
import com.example.meeTeam.evaluation.dto.EvaluationDTO;
import com.example.meeTeam.evaluation.dto.EvaluationRequestDTO;
import com.example.meeTeam.evaluation.dto.EvaluationResponseDTO;
import com.example.meeTeam.member.model.Member;
import com.example.meeTeam.member.dto.MemberDetails;

import java.util.List;

public interface EvaluationService {
    void updateScore(Member member);

    void doEvaluation(MemberDetails memberDetails, EvaluationRequestDTO.doingEvaluationDTO evaluationDTO);
    void beforeDoingEvaluation(Member member, Member targetMember);
    void deleteEvaluation(EvaluationDTO evaluationDTO);

    List<Evaluation> getEvalutaionByMember(Member member);
    List<EvaluationResponseDTO.beforeEvaluation> beforeDoingEvaluationListByMember(MemberDetails member);
}
