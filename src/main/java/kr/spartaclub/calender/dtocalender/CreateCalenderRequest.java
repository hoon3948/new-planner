package kr.spartaclub.calender.dtocalender;

import lombok.Getter;

@Getter
public class CreateCalenderRequest {
    private String title;
    private String content;
    private Long profileId;
    private String password;
}
