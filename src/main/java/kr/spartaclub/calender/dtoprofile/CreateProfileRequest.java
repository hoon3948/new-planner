package kr.spartaclub.calender.dtoprofile;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateProfileRequest {
    private String name;
    private String email;
    private String password;
}
