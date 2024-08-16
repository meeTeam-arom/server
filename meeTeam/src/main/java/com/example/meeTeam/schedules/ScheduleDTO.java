package com.example.meeTeam.schedules;

import com.example.meeTeam.chatroom.Chatroom;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private Long id;
    private Chatroom chatroom;
    private String title;
    private String content;
    private boolean status;

    public static ScheduleDTO toDTO(Schedule schedule){
        return ScheduleDTO.builder()
                .id(schedule.getId())
                .chatroom(schedule.getChatroom())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .status(schedule.isStatus())
                .build();
    }
}
