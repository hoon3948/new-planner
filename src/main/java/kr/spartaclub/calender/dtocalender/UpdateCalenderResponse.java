package kr.spartaclub.calender.dtocalender;

import lombok.Builder;

@Builder
public record UpdateCalenderResponse(Long calenderId, String message) {
    public static UpdateCalenderResponse of(Long calenderId, String message) {
        return UpdateCalenderResponse.builder().calenderId(calenderId).message(message).build();
    }
}
