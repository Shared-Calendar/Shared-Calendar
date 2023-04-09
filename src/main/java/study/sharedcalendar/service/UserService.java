package study.sharedcalendar.service;

import static study.sharedcalendar.constant.ErrorCode.*;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.SignUpReq;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.exception.AuthorizationException;
import study.sharedcalendar.exception.DuplicateException;
import study.sharedcalendar.exception.NoMatchedUserException;
import study.sharedcalendar.mapper.UserMapper;

@Slf4j
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

		if (!emailAuthenticationCheck("authentic" + signUpReq.getEmail())) {
			throw new AuthorizationException(NOT_AUTHENTIC_EMAIL);
		}

		String encryptedPassword = encryptionService.encrypt(signUpReq.getPassword());
		SignUpReq signUpSignUpReq = SignUpReq.builder()
			.userId(signUpReq.getUserId())
			.password(encryptedPassword)
			.email(signUpReq.getEmail())
			.build();

		userMapper.createUser(signUpSignUpReq);
		redisService.deleteData("authentic " + signUpReq.getEmail());
	}

	public void userIdDuplicationCheck(String userId) {
		if (userIdExist(userId)) {
			throw new DuplicateException(ID_DUPLICATE);
		}
	}

	private boolean userIdExist(String userId) {
		return userMapper.userIdExist(userId);
	}

	public boolean emailExist(String email) {
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
		if (redisService.checkData(email, "authentic"))
			return true;
		return false;
	}

	public String findUserIdByEmail(String email) {
		String userId = userMapper.findUserIdByEmail(email);
		log.info("이메일로 찾은 아이디 ={} ", userId);
		if (userId == null) {
			throw new NoMatchedUserException(NO_MATCHING_USER_BY_EMAIL);
		}
		return userId;
	}

	public void resetPassword(String email, String password) {
		if (!emailAuthenticationCheck("pwd" + email)) {
			throw new AuthorizationException(NOT_AUTHENTIC_EMAIL);
		}

		String encryptedPassword = encryptionService.encrypt(password);
		userMapper.resetPassword(email, encryptedPassword);
		redisService.deleteData("pwd " + email);
	}

	private boolean userExistById(int id) {
		if (!userMapper.userExistById(id)) {
			throw new NoMatchedUserException(NO_MATCHING_USER_BY_ID);
		}
		return true;
	}

	public User findUserById(int id) {
		if (!userExistById(id)) {
			throw new NoMatchedUserException(NO_MATCHING_USER_BY_ID);
		}

		User user = userMapper.findUserById(id);

		return user;
	}

}
