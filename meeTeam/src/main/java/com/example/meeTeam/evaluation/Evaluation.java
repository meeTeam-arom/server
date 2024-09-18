package com.example.meeTeam.evaluation;

import com.example.meeTeam.global.entity.BaseEntity;
import com.example.meeTeam.member.model.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Evaluation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double evaluationScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_evaluation")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member targetMember;

    private boolean complete;

    @Builder
    public Evaluation(double evaluationScore, Member member,Member targetMember, boolean complete){
        this.evaluationScore = evaluationScore;
        this.member = member;
        this.targetMember = targetMember;
        this.complete = complete;
    }

}
