package kr.spartaclub.calender.dtocalender;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kr.spartaclub.calender.dtocomment.GetCommentResponse;
import kr.spartaclub.calender.entity.Calender;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({
        "calenderId",
        "title",
        "content",
        "profileId",
        "createdAt",
        "modifiedAt",
        "comments"
})
@Getter
public class GetSingleCalenderResponse {
    private final Long calenderId;
    private final String title;
    private final String content;
    private final Long profileId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<GetCommentResponse> comments;

    public GetSingleCalenderResponse(Calender calender) {
        this.calenderId = calender.getCalenderId();
        this.title = calender.getTitle();
        this.content = calender.getContent();
        this.profileId = calender.getProfile().getUserId();
        this.createdAt = calender.getCreatedAt();
        this.modifiedAt = calender.getModifiedAt();
        this.comments = calender.getComments()
                .stream()
                .map(GetCommentResponse::new)
                .toList();
    }
}
