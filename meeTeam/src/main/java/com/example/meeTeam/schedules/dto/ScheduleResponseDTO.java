package com.example.meeTeam.schedules.dto;

public class ScheduleResponseDTO {
    public record listSchedule (
            String title,
            String content,
            String deadline
    ){}



}
