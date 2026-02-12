package kr.spartaclub.calender.exception;

import lombok.Getter;

@Getter
public class ProfileException extends RuntimeException{

    private final ErrorCode errorCode;

    public ProfileException(ErrorCode errorCode) {
        super(errorCode.getMessage()); // 부모(RuntimeException)에게 메시지 전달
        this.errorCode = errorCode;
    }
}
