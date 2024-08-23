package com.example.meeTeam.evaluation;

import com.example.meeTeam.chatroom.dto.ChatroomDTO;
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
    public BaseResponse<?> saveEvaluation(@AuthenticationPrincipal MemberDetails member, @RequestBody EvaluationDTO.doingEvaluationDTO evaluationDTO) {
        evaluationService.doEvaluation(member, evaluationDTO);
        return BaseResponse.onSuccess("success");
    }

}


