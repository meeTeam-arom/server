package com.example.meeTeam.schedules;

import com.example.meeTeam.chatroom.Chatroom;

public class ScheduleRequestDTO {

    public record saveSchedule (
        Chatroom chatroom,
        String title,
        String content,
        String deadline
    ){}

}
