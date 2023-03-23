package study.sharedcalendar.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.SignUpReq;
import study.sharedcalendar.service.LoginService;
import study.sharedcalendar.service.UserService;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	private final LoginService loginService;

	@PutMapping("/sign-up")
	public void signUp(@RequestBody @Valid SignUpReq signUpReq) {
		userService.signUp(signUpReq);
	}

	@GetMapping
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

	@DeleteMapping()
	public void deleteAccount(@RequestBody @Valid LoginReq loginReq) {
		loginService.logout();
		userService.deleteUser(loginReq);
	}
}