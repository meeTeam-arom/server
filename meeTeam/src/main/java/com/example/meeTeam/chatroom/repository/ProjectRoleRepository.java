package com.example.meeTeam.chatroom.repository;

import com.example.meeTeam.chatroom.MemberChatroom;
import com.example.meeTeam.chatroom.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRoleRepository extends JpaRepository<ProjectRole, Long> {
//    @Query(value = "SELECT u FROM ProjectRole u WHERE u.memberChatroom = :#{targetMemberChatroom}", nativeQuery = true)
//    List<ProjectRole> findRoleByMemberChatroom(@Param("targetMemberChatroom") MemberChatroom targetMemberChatroom);
    Optional<ProjectRole> findByMemberChatroom(MemberChatroom memberChatroom);
}
