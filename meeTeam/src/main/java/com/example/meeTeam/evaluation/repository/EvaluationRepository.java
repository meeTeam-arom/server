package com.example.meeTeam.evaluation.repository;

import com.example.meeTeam.evaluation.Evaluation;
import com.example.meeTeam.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByMember(Member member);
}
