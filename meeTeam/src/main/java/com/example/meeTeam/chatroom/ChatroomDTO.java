package com.example.meeTeam.chatroom;

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

    @Getter
    @Setter
    public static class chatroomCreate{
        private String chatroomName;
        private int totoalMember ;
        private boolean wantLeader;
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
    }

    public static ChatroomDTO toDTO(Chatroom chatroom){
        return ChatroomDTO.builder()
                .id(chatroom.getId())
                .chatroomName(chatroom.getChatroomName())
                .totalMember(chatroom.getTotalMember())
                .code(chatroom.getCode())
                .memberChatroomList(chatroom.getMemberChatroomList())
                .schedules(chatroom.getSchedules())
                .build();
    }
}
