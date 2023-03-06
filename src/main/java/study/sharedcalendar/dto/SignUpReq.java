package study.sharedcalendar.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpReq {

	@NotBlank
	@Pattern(message = "잘못된 아이디 형식입니다."
		, regexp = "^[a-z0-9_-]{3,10}")
	private String userId;

	@NotBlank
	@Pattern(message = "잘못된 비밀번호 형식입니다."
		, regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,15}")
	private String password;

	@NotBlank
	@Email(message = "잘못된 이메일 형식입니다.")
	private String email;

	private String inviteUrlCode;
}