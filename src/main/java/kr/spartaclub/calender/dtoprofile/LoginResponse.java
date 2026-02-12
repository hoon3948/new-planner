package kr.spartaclub.calender.dtoprofile;

import lombok.Builder;

@Builder
public record LoginResponse(String message) {
    public static LoginResponse of(String message){
        return LoginResponse.builder()
                .message(message).build();
    }
}
