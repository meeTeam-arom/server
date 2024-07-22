package com.example.meeTeam.member;

import com.example.meeTeam.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String member_name;

    private String member_password;

    private String member_phone_num;

    private String member_email;

    private String member_role;

    private double member_manner_temp;

    private Long member_used_coins;

    private Long member_earned_coins;

    private String created_at;

    private String updated_at;

    private double latitude;

    private double longitude;

    private String available_date;
}
