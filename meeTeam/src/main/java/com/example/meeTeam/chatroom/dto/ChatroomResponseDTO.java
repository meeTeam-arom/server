package com.example.meeTeam.chatroom.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ChatroomResponseDTO {
    public record enterSuccessMessage(
            String chatroomName,
            int totalMember,
            String leaderName,
            List<String> members
    ){}

    public record chatroomList(
            long id,
            String chatroomName,
            int totalMember
    ){}

    public static chatroomList toList(ChatroomDTO chatroomDTO){
        ChatroomResponseDTO.chatroomList chatroomList = new ChatroomResponseDTO.chatroomList(
                chatroomDTO.getId(),
                chatroomDTO.getChatroomName(),
                chatroomDTO.getTotalMember()
        );
        return chatroomList;
    }
}
