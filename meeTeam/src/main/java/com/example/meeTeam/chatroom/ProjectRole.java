package com.example.meeTeam.chatroom;

import com.example.meeTeam.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ProjectRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isWantLeader;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_chatroom_id")
    private MemberChatroom memberChatroom;

    public enum Role{
        LEADER, FOLLOWER
    }

    @Builder
    public ProjectRole(Role role, boolean isWantLeader,MemberChatroom memberChatroom){
        this.role = role;
        this.isWantLeader = isWantLeader;
        this.memberChatroom = memberChatroom;
    }
}
