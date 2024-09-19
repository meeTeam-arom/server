package com.example.meeTeam.chatroom.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ChatroomResponseDTO {
    public record EnterSuccessMessage(
            String chatroomName,
            int totalMember,
            String leaderName,
            List<String> members
    ){}

    public record ChatroomList(
            long id,
            String chatroomName,
            int totalMember
    ){}

    public static ChatroomList toList(ChatroomDTO chatroomDTO){
        ChatroomResponseDTO.ChatroomList chatroomList = new ChatroomResponseDTO.ChatroomList(
                chatroomDTO.getId(),
                chatroomDTO.getChatroomName(),
                chatroomDTO.getTotalMember()
        );
        return chatroomList;
    }


}
