package study.sharedcalendar.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Builder
public class User {
    private int id;

    @NotNull
    @Pattern(message = "잘못된 아이디 형식입니다."
            , regexp = "^[a-z0-9_-]{3,10}")
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    @Email(message = "잘못된 이메일 형식입니다.")
    private String email;

    private String inviteUrl;
}
