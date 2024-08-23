package com.example.meeTeam.member;

import com.example.meeTeam.chatroom.MemberChatroom;
import com.example.meeTeam.evaluation.Evaluation;
import com.example.meeTeam.global.entity.BaseEntity;
import com.example.meeTeam.orders.Orders;
import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String memberName;

    private String memberPassword;

    private String memberPhoneNum;

    private String memberEmail;

    private String memberRole;

    private double memberMannerTemp;

    private Long memberUsedCoins;

    private Long memberEarnedCoins;

    private double latitude;

    private double longitude;

    private String availableDate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Evaluation> evaluations = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberOAuth> memberOAuths = new ArrayList<>();


    @Builder
    public Member(String email, String memberName, String memberPassword, String memberPhoneNum, String memberEmail, String memberRole, double memberMannerTemp, Long memberUsedCoins, Long memberEarnedCoins, double latitude, double longitude, String availableDate) {
        this.email = email;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberPhoneNum = memberPhoneNum;
        this.memberEmail = memberEmail;
        this.memberRole = memberRole;
        this.memberMannerTemp = memberMannerTemp;
        this.memberUsedCoins = memberUsedCoins;
        this.memberEarnedCoins = memberEarnedCoins;
        this.latitude = latitude;
        this.longitude = longitude;
        this.availableDate = availableDate;
    }

    public void updateMannerTemp(double memberMannerTemp){
        this.memberMannerTemp = memberMannerTemp;
    }

}
