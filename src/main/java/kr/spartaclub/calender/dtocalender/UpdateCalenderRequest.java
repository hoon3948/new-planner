package kr.spartaclub.calender.dtocalender;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCalenderRequest {
    private Long calenderId;
    private String title;
    private String content;
}
