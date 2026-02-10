package kr.spartaclub.calender.dto;

import kr.spartaclub.calender.entity.Calender;
import lombok.Getter;

@Getter
public class UpdateCalenderResponse {
    private final Long calenderId;

    public UpdateCalenderResponse(Calender calender) {
        this.calenderId = calender.getCalenderId();
    }
}
