package kr.spartaclub.calender.dto;

import kr.spartaclub.calender.entity.Calender;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCalenderResponse {

    private final Long calenderId;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCalenderResponse(Calender calender) {
        this.calenderId = calender.getCalenderId();
        this.title = calender.getTitle();
        this.content = calender.getContent();
        this.author = calender.getAuthor();
        this.createdAt = calender.getCreatedAt();
        this.modifiedAt = calender.getModifiedAt();
    }
}
