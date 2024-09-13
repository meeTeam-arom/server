package com.example.meeTeam.chatroom.dto;

import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.chatroom.MemberChatroom;
import com.example.meeTeam.member.Member;
import com.example.meeTeam.schedules.Schedule;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatroomDTO {
    private Long id;
    private String chatroomName;
    private int totalMember;
    private String code;
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();
    private List<Schedule> schedules = new ArrayList<>();
    private boolean status;
    private Member makePerson;



    public static ChatroomDTO toDTO(Chatroom chatroom){
        return ChatroomDTO.builder()
                .id(chatroom.getId())
                .chatroomName(chatroom.getChatroomName())
                .totalMember(chatroom.getTotalMember())
                .code(chatroom.getCode())
                .memberChatroomList(chatroom.getMemberChatroomList())
                .schedules(chatroom.getSchedules())
                .status(chatroom.isStatus())
                .makePerson(chatroom.getMakePerson())
                .build();
    }
}
