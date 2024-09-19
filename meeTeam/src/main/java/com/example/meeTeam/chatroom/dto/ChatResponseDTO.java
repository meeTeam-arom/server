package com.example.meeTeam.chatroom.dto;

public class ChatResponseDTO {
    public record AnonymousMessage(
            Long chatroomId,
            Long receiveMemberId,
            String content
    ){}
}
