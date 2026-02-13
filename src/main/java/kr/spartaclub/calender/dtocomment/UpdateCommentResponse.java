package kr.spartaclub.calender.dtocomment;

import lombok.Builder;

@Builder
public record UpdateCommentResponse(Long commentId, String message){

    public static UpdateCommentResponse of(Long commentId, String message) {
        return UpdateCommentResponse.builder()
                .commentId(commentId).message(message).build();
    }
}
