package com.example.meeTeam.chatroom;

import com.example.meeTeam.chatroom.dto.ChatroomDTO;
import com.example.meeTeam.global.entity.BaseEntity;
import com.example.meeTeam.member.model.Member;
import com.example.meeTeam.schedules.Schedule;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chatroom")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Chatroom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatroomName;

    private int totalMember;

    private String code;

    private boolean status;

    @OneToMany(mappedBy = "chatroom")
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();

    @OneToMany(mappedBy = "chatroom")
    private List<Schedule> schedules = new ArrayList<>();

    //방 만든 사람도 추가해야할 것 같음.
    @ManyToOne
    private Member makePerson;

    @Builder
    public Chatroom(Long id, String chatroomName, int totalMember, String code, List<MemberChatroom> memberChatroomList,List<Schedule> schedules,boolean status,Member makePerson) {
        this.id = id;
        this.chatroomName = chatroomName;
        this.totalMember = totalMember;
        this.code = code;
        this.memberChatroomList = memberChatroomList;
        this.schedules = schedules;
        this.status = status;
        this.makePerson = makePerson;
    }

    public static Chatroom toEntity(ChatroomDTO chatroomDTO){
        return new Chatroom(
                chatroomDTO.getId(),
                chatroomDTO.getChatroomName(),
                chatroomDTO.getTotalMember(),
                chatroomDTO.getCode(),
                chatroomDTO.getMemberChatroomList(),
                chatroomDTO.getSchedules(),
                chatroomDTO.isStatus(),
                chatroomDTO.getMakePerson()
        );
    }


    public void addMemberChatroom(MemberChatroom memberChatroom) {
        memberChatroom.setChatroom(this);
        memberChatroomList.add(memberChatroom);
    }

    public void addSchedule(Schedule schedule) {

    }
}