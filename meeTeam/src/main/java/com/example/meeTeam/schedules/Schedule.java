package com.example.meeTeam.schedules;

import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "schedule")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_schedule")
    private Chatroom chatroom;

    private String title;

    private String content;

    private boolean status;

    private String deadline;

    @Builder
    public Schedule(Chatroom chatroom, String title, String content, boolean status, String deadline){
        this.chatroom = chatroom;
        this.title = title;
        this.content = content;
        this.status = status;
        this.deadline = deadline;
    }
}