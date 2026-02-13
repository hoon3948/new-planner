package kr.spartaclub.calender.dtocomment;

import lombok.Builder;

@Builder
public record CreateCommentResponse (String content, String message){
    public static CreateCommentResponse of(String content,String message){
        return CreateCommentResponse.builder()
                .content(content).message(message).build();
    }
}

