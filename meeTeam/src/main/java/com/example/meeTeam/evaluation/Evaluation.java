package com.example.meeTeam.evaluation;

import com.example.meeTeam.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
    private double evaluation_score;

    @Column
    private String evaluated_member;
}
