package com.example.meeTeam.chatroom;

import com.example.meeTeam.global.entity.BaseEntity;
import com.example.meeTeam.schedules.Schedule;
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
public class Chatroom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatroomName;

    private int totalMember;

    @OneToMany(mappedBy = "chatroom")
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();

    @OneToMany(mappedBy = "chatroom")
    private List<Schedule> schedules = new ArrayList<>();

}