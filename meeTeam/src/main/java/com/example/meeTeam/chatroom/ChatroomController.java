package com.example.meeTeam.chatroom;

import com.example.meeTeam.global.exception.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatroomController {

    @Autowired
    private final ChatroomService chatroomService;

    @PostMapping("/room/create")
    public BaseResponse<?> createChatroom(@RequestBody ChatroomDTO.chatroomCreate chatroomCreate){
        return BaseResponse.onSuccess(chatroomService.createChatroom(chatroomCreate).getCode());
    }

    @PostMapping("/room/test")
    public BaseResponse<?> test(@RequestBody ChatroomDTO.enterChatroom enterChatroom){
        chatroomService.test(enterChatroom);
        return BaseResponse.onSuccess("test success");
    }

    @PostMapping("/room/enter")
    public BaseResponse<?> enterChatroom(@RequestBody ChatroomDTO.enterChatroom enterChatroom){
        return BaseResponse.onSuccess(chatroomService.enterChatroom(enterChatroom));
    }

//    ==================  밑에는 만들어야함


}
