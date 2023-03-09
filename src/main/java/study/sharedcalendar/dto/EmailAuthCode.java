package study.sharedcalendar.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class EmailAuthCode {
	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String authCode;
}
