package study.sharedcalendar.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class LoginReq {
	@NotBlank
	private String userId;

	@NotBlank
	private String password;
}
