package com.example.meeTeam.evaluation.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class EvaluationRequestDTO {

    public record doingEvaluationDTO(
        List<String> targetMembersEmail,
        List<Integer> scoreList
   ) {}

}
