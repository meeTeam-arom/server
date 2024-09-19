package com.example.meeTeam.evaluation.controller;

import com.example.meeTeam.evaluation.dto.EvaluationDTO;
import com.example.meeTeam.evaluation.dto.EvaluationRequestDTO;
import com.example.meeTeam.evaluation.service.EvaluationService;
import com.example.meeTeam.global.exception.BaseResponse;
import com.example.meeTeam.member.dto.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EvaluationController {

    @Autowired
    private final EvaluationService evaluationService;

    @PostMapping("/evaluation")
    public BaseResponse<?> saveEvaluation(@AuthenticationPrincipal MemberDetails member, @RequestBody EvaluationRequestDTO.doingEvaluationDTO evaluationDTO) {
        evaluationService.doEvaluation(member, evaluationDTO);
        return BaseResponse.onSuccess("success");
    }

    @GetMapping("/evaluation/beforeList")
    public BaseResponse<?> beforeEvaluation(@AuthenticationPrincipal MemberDetails member) {
        return BaseResponse.onSuccess(evaluationService.beforeDoingEvaluationListByMember(member));
    }
}


