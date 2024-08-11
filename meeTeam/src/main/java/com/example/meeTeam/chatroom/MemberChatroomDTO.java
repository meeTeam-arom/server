package com.example.meeTeam.chatroom;

import com.example.meeTeam.member.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
