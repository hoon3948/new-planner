package kr.spartaclub.calender.dtoprofile;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kr.spartaclub.calender.entity.Profile;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "userId",
        "name",
        "createdAt",
        "modifiedAt"
})
@Getter
public class GetProfileResponse {

    private final Long userId;
    private final String name;
    private final LocalDateTime createdAT;
    private final LocalDateTime modifiedAt;

    public GetProfileResponse(Profile profile) {
        this.userId = profile.getUserId();
        this.name = profile.getName();
        this.createdAT = profile.getCreatedAt();
        this.modifiedAt = profile.getModifiedAt();
    }
}
