package com.example.meeTeam.chatroom.dto;

import com.example.meeTeam.chatroom.MemberChatroom;
import com.example.meeTeam.chatroom.ProjectRole;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRoleDTO {

    private Long id;
    private ProjectRole.Role role;
    private MemberChatroom memberChatroom;
    private boolean isWantLeader;

    public enum Role{
        LEADER, FOLLOWER
    }
}
