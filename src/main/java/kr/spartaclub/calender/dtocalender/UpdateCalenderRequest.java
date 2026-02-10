package kr.spartaclub.calender.dtocalender;

import lombok.Getter;

@Getter
public class UpdateCalenderRequest {
    private Long calenderId;
    private String title;
    private String author;
    private String password;
}
