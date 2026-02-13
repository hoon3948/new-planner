package kr.spartaclub.calender.dtoprofile;

import lombok.Builder;

@Builder
public record DeleteProfileResponse(String message) {
    public static DeleteProfileResponse of(String message){
        return DeleteProfileResponse.builder().message(message).build();
    }
}
