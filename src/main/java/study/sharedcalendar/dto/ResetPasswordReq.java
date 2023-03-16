package study.sharedcalendar.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public class ResetPasswordReq {
	@NotBlank
	@Email(message = "잘못된 이메일 형식입니다.")
	private String email;

	@NotBlank
	@Pattern(message = "잘못된 비밀번호 형식입니다."
		, regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,15}")
	private String password;
}
