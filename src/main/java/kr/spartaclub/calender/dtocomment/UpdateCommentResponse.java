package kr.spartaclub.calender.dtocomment;

import kr.spartaclub.calender.entity.Comment;
import lombok.Getter;

@Getter
public class UpdateCommentResponse {
    private final Long commentId;

    public UpdateCommentResponse(Comment comment) {
        this.commentId = comment.getCommentId();
    }
}
