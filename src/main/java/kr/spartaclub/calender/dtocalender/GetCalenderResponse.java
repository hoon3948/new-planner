package kr.spartaclub.calender.dtocalender;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kr.spartaclub.calender.entity.Calender;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "calenderId",
        "title",
        "content",
        "profileId",
        "createdAt",
        "modifiedAt"
})
@Getter
public class GetCalenderResponse {

    private final Long calenderId;
    private final String title;
    private final String content;
    private final Long profileId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCalenderResponse(Calender calender) {
        this.calenderId = calender.getCalenderId();
        this.title = calender.getTitle();
        this.content = calender.getContent();
        this.profileId = calender.getProfile().getUserId();
        this.createdAt = calender.getCreatedAt();
        this.modifiedAt = calender.getModifiedAt();
    }
}
