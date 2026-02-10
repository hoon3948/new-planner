package kr.spartaclub.calender.dto;

import kr.spartaclub.calender.entity.Calender;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCalenderResponse {
    private final Long calenderId;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAT;
    private final LocalDateTime modifiedAt;


    public CreateCalenderResponse(Calender calender) {
        this.calenderId = calender.getCalenderId();
        this.title = calender.getTitle();
        this.content = calender.getContent();
        this.author = calender.getAuthor();
        this.createdAT = calender.getCreatedAt();
        this.modifiedAt = calender.getModifiedAt();
    }
}
