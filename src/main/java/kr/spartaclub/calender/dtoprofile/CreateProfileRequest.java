package kr.spartaclub.calender.dtoprofile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateProfileRequest {
    @NotBlank(message = "이름은 필수로 입력해야합니다.")
    private String name;
    @Email
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상 입력해야 합니다.")
    private String password;
}
