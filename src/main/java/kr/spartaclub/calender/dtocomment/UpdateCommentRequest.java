package kr.spartaclub.calender.dtocomment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateCommentRequest {
    private String content;
}
