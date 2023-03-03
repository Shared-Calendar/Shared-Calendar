package study.sharedcalendar.controller;

import javax.mail.MessagingException;
import javax.validation.constraints.Email;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.service.MailService;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class MailController {
	private final MailService mailService;

	@PostMapping
	public void sendAuthEmail(@RequestParam @Email String email) throws MessagingException {
		mailService.sendAuthEmail(email);
	}

	@PostMapping("/auth")
	public void sendEmailAuthCode(@RequestParam @Email String email, @RequestParam String authCode) {
		mailService.checkEmailAuthCode(email, authCode);
	}
}
