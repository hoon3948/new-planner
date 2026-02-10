package kr.spartaclub.calender.dtoprofile;

import kr.spartaclub.calender.entity.Profile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateProfileResponse {
    private final Long userId;
    private final String name;
    private final String email;
    private final LocalDateTime createdAT;
    private final LocalDateTime modifiedAt;


    public CreateProfileResponse(Profile profile) {
        this.userId = profile.getUserId();
        this.name = profile.getName();
        this.email = profile.getEmail();
        this.createdAT = profile.getCreatedAt();
        this.modifiedAt = profile.getModifiedAt();
    }
}
