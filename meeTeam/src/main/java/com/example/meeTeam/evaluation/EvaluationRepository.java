package com.example.meeTeam.evaluation;

import com.example.meeTeam.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByMember(Member member);
}
