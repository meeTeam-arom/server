package com.example.meeTeam.member.service;

import com.example.meeTeam.global.exception.BaseException;
import com.example.meeTeam.global.exception.codes.ErrorCode;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
@Slf4j
public class CoolSmsService {

    private final DefaultMessageService messageService;
    @Value("${coolsms.api.number}") String fromPhoneNumber;

    public CoolSmsService(
            @Value("${coolsms.api.key}") String apiKey,
            @Value("${coolsms.api.secret}") String apiSecret) {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
    }

    public SingleMessageSentResponse sendSms(String to, HttpSession session){

        String code = generateRandomNumber();
        Message message = new Message();

        message.setFrom(to);
        message.setTo(fromPhoneNumber);
        message.setText("[meeTeam]\n인증번호는 [" + code + "] 입니다.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        session.setAttribute("message_id", response.getMessageId());
        session.setAttribute("code", code);
        session.setMaxInactiveInterval(180);

        return response;
    }

    public String validateSMS(String code, HttpSession session){
        if(session.getAttribute("message_id") == null || session.getAttribute("code") == null) return "인증번호 만료, 처음부터 다시 시도해주세요";
        if(session.getAttribute("code").equals(code)) return "인증 완료";
        return "인증 번호가 일치하지 않습니다";
    }

    // 랜덤한 4자리 숫자 생성 메서드
    private String generateRandomNumber() {
        Random rand = new Random();
        StringBuilder numStr = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            numStr.append(rand.nextInt(10));
        }
        return numStr.toString();
    }
}
