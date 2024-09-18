package com.example.meeTeam.global.exception.codes;

import com.example.meeTeam.global.exception.codes.reason.Reason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode implements BaseCode {
    NOT_VALID_ERROR(HttpStatus.NOT_FOUND, "G011", "잘못됨"),

    //상품 없음
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "ITEM-0000", "아이템이 존재하지 않습니다."),

    //방이 없음
    NO_CHATROOM_PARTICIPATED(HttpStatus.BAD_REQUEST, "CHATROOM-0000", "참여 중인 채팅방이 존재하지 않습니다"),

    //인원 초과됨
    OVER_NUMBER_PEOPLE(HttpStatus.BAD_REQUEST,"G013","인원 수가 초과되어 입장 불가능"),

    //방 이미 입장함
    ALREADY_ENTER_ROOM(HttpStatus.BAD_REQUEST,"G014","현재 방에 이미 입장을 함"),
    
    //방을 찾을수 없음
    ROOM_NOT_FOUND(HttpStatus.BAD_REQUEST,"G015","방을 찾을 수 없음"),
    // 4xx : client error
    
    //일정없음
    SCHEDULE_NOT_FOUND(HttpStatus.BAD_REQUEST,"G016","일정을 찾을 수 없음"),

    //평가할거 없음
    EVALUATION_NOT_FOUND(HttpStatus.BAD_REQUEST,"G017","평가항목 찾을 수 없음"),

    SHORT_NUMBER_PEOPLE(HttpStatus.BAD_REQUEST,"G018","방 인원이 아직 모자름"),

    NOT_VALID_NUMBER_PEOPLE(HttpStatus.BAD_REQUEST,"G019","방 인원수 설정이 맞지 않음"),

    NOT_VALID_ROLE(HttpStatus.BAD_REQUEST,"G020","리더가 아니라 해당 기능은 불가"),
    
    //자잘한 에러
    SEARCH_KEYWORD_TOO_SHORT(HttpStatus.BAD_REQUEST, "KEYWORD-0000", "검색어는 3글자부터 입력하세요."),

    //바인딩 에러
    BINDING_ERROR(HttpStatus.BAD_REQUEST, "BINDING-0000", "바인딩에 실패했습니다."),

    //로그인 에러
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "LOGIN-0001", "이메일이 잘못됨"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON-0000", "잘못된 요청입니다."),
    EXIST_EMAIL(HttpStatus.BAD_REQUEST, "COMMON-0002", "이미 존재하는 회원입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER-0000", "존재하지 않는 회원입니다."),
    PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "LOGIN-0000", "잘못된 비밀번호입니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "TOKEN-0001", "토큰ㅇ"),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN-0000", "토큰 오류"),
    EMPTY_TOKEN_PROVIDED(HttpStatus.UNAUTHORIZED, "TOKEN-0002", "토큰 텅텅"),
    INVALID_EMAIL_OR_PASSWORD(HttpStatus.NOT_FOUND, "MEMBER-0001", "유효하지 않는 이메일, 비번"),

    //coolsms 에러
    COOOLSMS_ERROR(HttpStatus.BAD_REQUEST, "SMS-0000", "Cool SMS 오류"),

    // 5xx : server error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER-0000", "서버 에러");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public Reason.ReasonDto getReasonHttpStatus() {
        return Reason.ReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
