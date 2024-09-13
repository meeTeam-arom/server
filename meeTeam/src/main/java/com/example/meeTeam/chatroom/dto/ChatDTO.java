package com.example.meeTeam.chatroom.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private Long id;
    private String content;
}
