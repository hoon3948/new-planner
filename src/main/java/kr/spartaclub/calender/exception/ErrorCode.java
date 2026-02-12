package kr.spartaclub.calender.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Profile 관련 에러 (P으로 시작)
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "P001", "이미 존재하는 이메일입니다."),
    DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST, "P002", "이미 존재하는 사용자 이름입니다."),
    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "P003", "회원을 찾을 수 없습니다."),
    CALENDER_NOT_FOUND(HttpStatus.NOT_FOUND, "P004", "일정을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "P005", "댓글을 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "P006", "비밀번호가 일치하지 않습니다."),
    INVALID_PROFILE(HttpStatus.BAD_REQUEST, "P007", "사용자가 일치하지 않습니다."),
    STATE_NOT_LOGIN(HttpStatus.BAD_REQUEST,"P008","로그인되어있지않습니다."),

    // 공통 에러 (C로 시작)
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "잘못된 입력값입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C002", "서버 내부 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
