package kr.spartaclub.calender.dtocomment;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kr.spartaclub.calender.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "commentId",
        "calenderId",
        "profileId",
        "content",
        "createdAt",
        "modifiedAt"
})
@Getter
public class CreateCommentResponse {
    private final Long commentId;
    private final Long calenderId;
    private final Long profileId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public CreateCommentResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.calenderId = comment.getCalender().getCalenderId();
        this.profileId = comment.getProfile().getUserId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
