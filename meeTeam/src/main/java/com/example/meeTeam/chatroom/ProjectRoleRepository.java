package com.example.meeTeam.chatroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRoleRepository extends JpaRepository<ProjectRole, Long> {
//    @Query(value = "SELECT u FROM ProjectRole u WHERE u.memberChatroom = :#{targetMemberChatroom}", nativeQuery = true)
//    List<ProjectRole> findRoleByMemberChatroom(@Param("targetMemberChatroom") MemberChatroom targetMemberChatroom);
    ProjectRole findByMemberChatroom(MemberChatroom memberChatroom);
}
