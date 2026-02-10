package kr.spartaclub.calender.dtocalender;

import kr.spartaclub.calender.entity.Calender;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCalenderResponse {
    private final Long calenderId;
    private final String title;
    private final String content;
    private final Long profileId;
    private final LocalDateTime createdAT;
    private final LocalDateTime modifiedAt;


    public CreateCalenderResponse(Calender calender) {
        this.calenderId = calender.getCalenderId();
        this.title = calender.getTitle();
        this.content = calender.getContent();
        this.profileId = calender.getProfile().getUserId();
        this.createdAT = calender.getCreatedAt();
        this.modifiedAt = calender.getModifiedAt();
    }
}
