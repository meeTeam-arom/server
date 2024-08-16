package com.example.meeTeam.chatroom.dto;

import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.chatroom.MemberChatroom;
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

    @Getter
    @Setter
    public static class chatroomCreate{
        private String chatroomName;
        private int totoalMember ;
        private boolean wantLeader;
    }

    @Getter
    @Setter
    public static class chatroomId{
        private Long id;
    }

    @Getter
    @Setter
    public static class chatroomCode{
        private String code;
    }

    @Getter
    @Setter
    public static class enterChatroom{
        private String code;
        private boolean wantLeader;
        private int totalMember;
    }

    @Getter
    @Setter
    public static class enterSuccessMessage{
        private String chatroomName;
        private int totalMember;
        private String leaderName;
        private List<String> members;
    }

    @Getter
    @Setter
    public static class chatroomList{
        private long id;
        private String chatroomName;
        private int totalMember;
    }

    public static chatroomList toList(ChatroomDTO chatroomDTO){
        ChatroomDTO.chatroomList chatroomList = new ChatroomDTO.chatroomList();
        chatroomList.setId(chatroomDTO.getId());
        chatroomList.setChatroomName(chatroomDTO.getChatroomName());
        chatroomList.setTotalMember(chatroomDTO.getTotalMember());
        return chatroomList;
    }

    public static ChatroomDTO toDTO(Chatroom chatroom){
        return ChatroomDTO.builder()
                .id(chatroom.getId())
                .chatroomName(chatroom.getChatroomName())
                .totalMember(chatroom.getTotalMember())
                .code(chatroom.getCode())
                .memberChatroomList(chatroom.getMemberChatroomList())
                .schedules(chatroom.getSchedules())
                .status(chatroom.isStatus())
                .build();
    }
}
