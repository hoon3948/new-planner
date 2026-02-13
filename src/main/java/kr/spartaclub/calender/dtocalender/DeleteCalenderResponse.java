package kr.spartaclub.calender.dtocalender;

import lombok.Builder;

@Builder
public record DeleteCalenderResponse(String message) {
    public static DeleteCalenderResponse of(String message){
        return DeleteCalenderResponse.builder()
                .message(message).build();
    }
}
