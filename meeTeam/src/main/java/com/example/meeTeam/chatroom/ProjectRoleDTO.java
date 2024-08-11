package com.example.meeTeam.chatroom;

import jakarta.persistence.*;
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
