package study.sharedcalendar.service;

import static study.sharedcalendar.constant.ErrorCode.*;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.SignUpReq;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.exception.AuthorizationException;
import study.sharedcalendar.exception.DuplicateException;
import study.sharedcalendar.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserMapper userMapper;
	private final EncryptionService encryptionService;
	private final RedisService redisService;

	public void signUp(SignUpReq signUpReq) {
		if (userIdExist(signUpReq.getUserId())) {
			throw new DuplicateException(ID_DUPLICATE);
		}

		if (emailExist(signUpReq.getEmail())) {
			throw new DuplicateException(EMAIL_DUPLICATE);
		}
		if (!emailAuthenticationCheck(signUpReq.getEmail())) {
			throw new AuthorizationException(NOT_AUTHENTIC_EMAIL);
		}

		String encryptedPassword = encryptionService.encrypt(signUpReq.getPassword());
		SignUpReq signUpSignUpReq = SignUpReq.builder()
			.userId(signUpReq.getUserId())
			.password(encryptedPassword)
			.email(signUpReq.getEmail())
			.build();

		userMapper.createUser(signUpSignUpReq);
	}

	public void userIdDuplicationCheck(String userId) {
		if (userIdExist(userId)) {
			throw new DuplicateException(ID_DUPLICATE);
		}
	}

	private boolean userIdExist(String userId) {
		return userMapper.userIdExist(userId);
	}

	private boolean emailExist(String email) {
		return userMapper.emailExist(email);
	}

	public User findLoginUser(LoginReq loginReq) {
		return userMapper.findLoginUser(loginReq);
	}

	public void incrementLoginTryCount(int id) {
		userMapper.incrementLoginTryCount(id);
	}

	public void initLoginTryCount(int id) {
		userMapper.initLoginTryCount(id);
	}

	public int getPasswordDateDiff(int id) {
		return userMapper.getPasswordDateDiff(id);
	}

	private boolean emailAuthenticationCheck(String email) {
		String value = redisService.getData(email);

		if (value != null && value.equals("authentic")) {
			return true;
		}
		return false;
	}

}
