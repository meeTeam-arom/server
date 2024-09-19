package com.example.meeTeam.chatroom;

import com.example.meeTeam.chatroom.dto.ChatRequestDTO;
import com.example.meeTeam.global.entity.BaseEntity;
import com.example.meeTeam.member.model.Member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chatroom")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Chat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    @ManyToOne
    private Chatroom chatroom;

    @ManyToOne
    private Member sendMember;

    @ManyToOne
    private Member receiveMember;

    @Builder
    public Chat(Chatroom chatrooom, Member sendMember, Member receiveMember, String content){
        this.chatroom = chatroom;
        this.sendMember = sendMember;
        this.receiveMember = receiveMember;
        this. content = content;
    }

}