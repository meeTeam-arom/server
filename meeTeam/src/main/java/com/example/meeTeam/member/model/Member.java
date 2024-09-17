package com.example.meeTeam.member.model;

import com.example.meeTeam.chatroom.MemberChatroom;
import com.example.meeTeam.evaluation.Evaluation;
import com.example.meeTeam.global.entity.BaseEntity;
import com.example.meeTeam.member.dto.MemberRequest;
import com.example.meeTeam.mypage.dto.MyPageRequest;
import com.example.meeTeam.orders.OrderItem;
import jakarta.persistence.*;
import lombok.*;

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

    private String memberName;

    private String memberPassword;

    private String memberPhoneNum;

    private String memberEmail;

    private double memberMannerTemp;

    private Long memberUsedCoins;

    private Long memberEarnedCoins;

    private double latitude;

    private double longitude;

    private String availableDate;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Getter
    public enum Role {
        USER("USER"),
        ADMIN("ADMIN");

        Role(String name) {}

        private String name;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<OrderItem> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Evaluation> evaluations = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberOAuth> memberOAuths = new ArrayList<>();


    @Builder
    private Member(String memberName, String memberPassword, String memberPhoneNum, String memberEmail, double memberMannerTemp, Long memberUsedCoins, Long memberEarnedCoins, double latitude, double longitude, String availableDate) {
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberPhoneNum = memberPhoneNum;
        this.memberEmail = memberEmail;
        this.memberMannerTemp = memberMannerTemp;
        this.memberUsedCoins = memberUsedCoins;
        this.memberEarnedCoins = memberEarnedCoins;
        this.latitude = latitude;
        this.longitude = longitude;
        this.availableDate = availableDate;
    }

    public void encodePassword(String encodedPassword){
        this.memberPassword = encodedPassword;
    }

    public static Member createMember(MemberRequest.MemberSignupRequestDto dto) {
        return Member.builder()
                .memberPassword(dto.password())
                .memberEmail(dto.email())
                .memberPhoneNum(dto.phoneNumber())
                .memberMannerTemp(36.5)
                .memberEarnedCoins(0L)
                .memberUsedCoins(0L)
                .build();
    }

    public void addMemberAdditionInfo(MemberRequest.MemberAdditionInfoRequestDto additionInfo) {
        this.memberName = additionInfo.name();
        this.latitude = additionInfo.latitude();
        this.longitude = additionInfo.longitude();
    }

    public void updateMannerTemp(double memberMannerTemp){
        this.memberMannerTemp = memberMannerTemp;
    }

    public void updateMemberMyPage(MyPageRequest.MyPageUpdateRequestDto request){
        this.memberName = request.memberName();
        this.memberPhoneNum = request.memberPhoneNum();
        this.memberEmail = request.memberEmail();
        this.latitude = request.latitude();
        this.longitude = request.longitude();
        this.availableDate = request.availableDate();
    }
}
