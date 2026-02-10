package kr.spartaclub.calender.dto;

import lombok.Getter;

@Getter
public class CreateCalenderRequest {
    private String title;
    private String content;
    private String author;
    private String password;
}
