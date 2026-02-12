package kr.spartaclub.calender.dtocomment;

import kr.spartaclub.calender.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {
    private final Long commentId;
    private final Long calenderId;
    private final Long profileId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public GetCommentResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.calenderId = comment.getCalender().getCalenderId();
        this.profileId = comment.getProfile().getUserId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
