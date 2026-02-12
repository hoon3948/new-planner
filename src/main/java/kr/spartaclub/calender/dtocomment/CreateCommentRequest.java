package kr.spartaclub.calender.dtocomment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCommentRequest {
    private String content;
}
