package kr.spartaclub.calender.dtoprofile;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

@JsonPropertyOrder({
        "name",
        "message"
})
@Builder
public record CreateProfileResponse(
      String name,
      String message
){
    public static CreateProfileResponse of(String name,String message){
        return CreateProfileResponse.builder()
                .name(name).message(message).build();
    }
}
