package kr.spartaclub.calender.dtocalender;

import lombok.Builder;


@Builder
public record CreateCalenderResponse(String content,String message) {
    public static CreateCalenderResponse of(String content,String message) {
        return CreateCalenderResponse.builder().content(content).message(message).build();
    }
}
