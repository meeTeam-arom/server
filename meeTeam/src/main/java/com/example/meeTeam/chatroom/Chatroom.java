package com.example.meeTeam.chatroom;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chatroom")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatroomName;

    private int totalMember;

    @OneToMany(mappedBy = "chatroom")
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();
}