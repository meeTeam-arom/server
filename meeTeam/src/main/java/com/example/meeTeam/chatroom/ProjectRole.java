package com.example.meeTeam.chatroom;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProjectRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_chatroom_id")
    private MemberChatroom memberChatroom;

    public enum Role{

    }
}
