package com.example.meeTeam.chatroom.dto;

public class ChatRequestDTO {

    public record AnonymousMesssage(
            Long chatroomId,
            Long sendMemberId,
            Long receiveMemberId,
            String content
    ){}

}
