package study.sharedcalendar.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginReq {
    @NotBlank
    private String userId;

    @NotBlank
    private String password;
}
