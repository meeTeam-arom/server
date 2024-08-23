package com.example.meeTeam.chatroom.dto;

import lombok.Getter;
import lombok.Setter;

public class ChatroomRequestDTO {

    public record chatroomCreate(
            String chatroomName,
            int totoalMember,
            boolean wantLeader
    ){}

    public record chatroomId(
            Long id
    ){}

    public record chatroomCode(
            String code
    ){}

    public record enterChatroom(
             String code,
             boolean wantLeader,
             int totalMember
    ){}
}
