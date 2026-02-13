package kr.spartaclub.calender.dtocomment;

import lombok.Builder;

@Builder
public record DeleteCommentResponse(String message) {
    public static DeleteCommentResponse of(String message){
        return DeleteCommentResponse.builder()
                .message(message).build();
    }
}
