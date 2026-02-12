package kr.spartaclub.calender.dtoprofile;

import lombok.Builder;

@Builder
public record UpdateProfileResponse(
        String name,
        String message
) {
    public static UpdateProfileResponse of(String name, String message) {
        return UpdateProfileResponse.builder()
                .name(name).message(message).build();
    }
}
