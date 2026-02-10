package kr.spartaclub.calender.dtoprofile;

import lombok.Getter;

@Getter
public class UpdateProfileRequest {
    private Long userId;
    private String name;
    private String email;
    private String password;
}
