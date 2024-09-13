package com.example.meeTeam.chatroom;

import com.example.meeTeam.global.entity.BaseEntity;
import com.example.meeTeam.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_chatroom")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class MemberChatroom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "memberChatroom")
    private ProjectRole role;

    @Builder
    public MemberChatroom(Chatroom chatroom, Member member, ProjectRole role){
        this.id = id;
        this.member = member;
        this.role = role;
    }

    public void addProjectRole(ProjectRole role){
        role.setMemberChatroom(this);
        this.role = role;
    }



}
