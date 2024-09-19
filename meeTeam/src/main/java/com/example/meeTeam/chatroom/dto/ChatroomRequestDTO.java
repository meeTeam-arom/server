package com.example.meeTeam.chatroom.dto;

import com.example.meeTeam.member.model.Member;
import lombok.Getter;
import lombok.Setter;

public class ChatroomRequestDTO {

    public record ChatroomCreate(
            String chatroomName,
            int totoalMember,
            boolean wantLeader
    ){}

    public record ChatroomId(
            Long id
    ){}

    public record ChatroomCode(
            String code
    ){}

    public record EnterChatroom(
             String code,
             boolean wantLeader,
             int totalMember
    ){}
}
