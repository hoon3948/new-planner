package kr.spartaclub.calender.dtoprofile;

import lombok.Getter;

@Getter
public class SessionProfile {

    private final Long id;
    private final String name;
    private final String email;
    private final String password;

    public SessionProfile(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
