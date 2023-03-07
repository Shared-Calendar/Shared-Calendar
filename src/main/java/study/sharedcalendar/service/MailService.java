package study.sharedcalendar.service;

import static study.sharedcalendar.constant.ErrorCode.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.sharedcalendar.constant.ErrorCode;
import study.sharedcalendar.constant.MailConstant;
import study.sharedcalendar.exception.DuplicateException;
import study.sharedcalendar.exception.NoMatchedKeyException;
import study.sharedcalendar.exception.NoMatchedUserException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
	private final JavaMailSender mailSender;
	private final RedisService redisService;
	private final MailConstant mailConstant;
	private final UserService userService;

	public void sendAuthEmail(String email) throws MessagingException {
		if (userService.emailExist(email)) {
			throw new DuplicateException(EMAIL_DUPLICATE);
		}

		String authCode = createAuthCode(mailConstant.AUTH_CODE_LENGTH);
		log.info("인증 코드 길이 ={}", mailConstant.AUTH_CODE_LENGTH);
		log.info("인증 코드={}", authCode);

		String mailContent = createAuthMailContent(authCode);
		sendEmail("회원가입 이메일 인증", mailContent, email);

		redisService.setDataExpire(email, authCode, mailConstant.EXPIRE_TIME);
	}

	public void checkEmailAuthCode(String email, String authCode) {
		if (!redisService.checkData(email, authCode)) {
			throw new NoMatchedKeyException(ErrorCode.NO_MATCHING_AUTH_CODE);
		}
		redisService.setData("authentic" + email, "authentic");
	}

	private String createAuthCode(int size) {
		return RandomStringUtils.randomNumeric(size);
	}

	private String createAuthMailContent(String authCode) {
		return "<h1>[이메일 인증]</h1>"
			+ "<br>"
			+ "<h3>이메일 인증 번호 : " + authCode + "</h3>";
	}

	private void sendEmail(String subject, String mailContent, String email) throws MessagingException {
		MimeMessage mail = mailSender.createMimeMessage();
		mail.setSubject(subject, "utf-8");
		mail.setText(mailContent, "utf-8", "html");
		mail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		log.info("이메일 설정 완료");
		mailSender.send(mail);
	}
	public void findPwdByEmail(String email) throws MessagingException {
		if (!userService.emailExist(email)) {
			throw new NoMatchedUserException(NO_MATCHING_USER_BY_EMAIL);
		}
		sendPwdAuthEmail(email);
	}

	public void sendPwdAuthEmail(String email) throws MessagingException {
		if (!userService.emailExist(email)) {
			throw new NoMatchedUserException(NO_MATCHING_USER_BY_EMAIL);
		}

		String authCode = createAuthCode(mailConstant.AUTH_CODE_LENGTH);
		log.info("인증 코드 길이 ={}", mailConstant.AUTH_CODE_LENGTH);
		log.info("인증 코드={}", authCode);

		String mailContent = createAuthMailContent(authCode);
		sendEmail("비밀번호 재설정 이메일 인증", mailContent, email);

		redisService.setDataExpire(email, authCode, mailConstant.EXPIRE_TIME);
	}

	public void checkPwdEmailAuthCode(String email, String authCode) {
		if (!redisService.checkData(email, authCode)) {
			throw new NoMatchedKeyException(ErrorCode.NO_MATCHING_AUTH_CODE);
		}
		redisService.setData("pwd" + email, "authentic");
	}
}
