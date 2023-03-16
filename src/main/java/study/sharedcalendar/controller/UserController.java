package study.sharedcalendar.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.dto.EmailAuthCode;
import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.ResetPasswordReq;
import study.sharedcalendar.dto.SignUpReq;
import study.sharedcalendar.service.LoginService;
import study.sharedcalendar.service.MailService;
import study.sharedcalendar.service.UserService;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	private final LoginService loginService;
	private final MailService mailService;

	@PostMapping("/sign-up")
	public void signUp(@RequestBody @Valid SignUpReq signUpReq) {
		userService.signUp(signUpReq);
	}

	@GetMapping("/id-check")
	public void idCheck(
		@RequestParam
		@Pattern(message = "잘못된 아이디 형식입니다."
			, regexp = "^[a-z0-9_-]{3,10}")
		String userId) {
		userService.userIdDuplicationCheck(userId);
	}

	@PostMapping("/login")
	public void login(@RequestBody @Valid LoginReq loginReq) {
		loginService.login(loginReq);
	}

	@PostMapping("/logout")
	public void signOut() {
		loginService.logout();
	}

	@PostMapping("/email")
	public void sendAuthEmail(@RequestParam @Email String email) throws MessagingException {
		mailService.sendAuthEmail(email);
	}

	@PostMapping("/email-auth")
	public void checkEmailAuthCode(@RequestBody @Valid EmailAuthCode emailAuthCode) {
		mailService.checkEmailAuthCode(emailAuthCode.getEmail(), emailAuthCode.getAuthCode());
	}

	@GetMapping("/find-id")
	public String findUserIdByEmail(@RequestParam @Email @NotBlank String email) {
		return userService.findUserIdByEmail(email);
	}

	@GetMapping("/find-pwd")
	public void findPwdByEmail(@RequestParam @Email @NotBlank String email) throws MessagingException {
		mailService.findPwdByEmail(email);
	}

	@GetMapping("/pwd-email-auth")
	public void checkPwdEmailAuthCode(@RequestBody @Valid EmailAuthCode emailAuthCode) {
		mailService.checkPwdEmailAuthCode(emailAuthCode.getEmail(), emailAuthCode.getAuthCode());
	}

	@PostMapping("/reset")
	public void resetPassword(@RequestBody @Valid ResetPasswordReq resetPasswordReq) {
		userService.resetPassword(resetPasswordReq.getEmail(), resetPasswordReq.getPassword());
	}
}
