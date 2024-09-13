package com.example.meeTeam.schedules;

import com.example.meeTeam.chatroom.Chatroom;

public class ScheduleResponseDTO {
    public record listSchedule (
            String title,
            String content,
            String deadline
    ){}



}
