package com.example.meeTeam.chatroom;

import com.example.meeTeam.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
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


}