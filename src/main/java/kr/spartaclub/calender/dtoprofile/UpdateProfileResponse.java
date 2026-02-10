package kr.spartaclub.calender.dtoprofile;

import kr.spartaclub.calender.entity.Profile;
import lombok.Getter;

@Getter
public class UpdateProfileResponse {
    private final Long userId;

    public UpdateProfileResponse(Profile profile) {

        this.userId = profile.getUserId();
    }
}
