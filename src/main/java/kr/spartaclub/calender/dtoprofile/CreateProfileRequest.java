package kr.spartaclub.calender.dtoprofile;

import lombok.Getter;

@Getter
public class CreateProfileRequest {
    private String name;
    private String email;
    private String password;
}
