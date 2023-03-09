package study.sharedcalendar.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class ResetPasswordReq {
	@NotBlank
	private String email;
	@NotBlank
	private String password;
}
