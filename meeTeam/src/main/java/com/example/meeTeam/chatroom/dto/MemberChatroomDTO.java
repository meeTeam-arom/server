package com.example.meeTeam.chatroom.dto;

import com.example.meeTeam.chatroom.Chatroom;
import com.example.meeTeam.chatroom.ProjectRole;
import com.example.meeTeam.member.Member;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberChatroomDTO {
    private Long id;
    private Chatroom chatroom;
    private Member member;
    private ProjectRole role;
}
