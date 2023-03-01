package study.sharedcalendar.service;

import static study.sharedcalendar.constant.ErrorCode.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.constant.UserConstant;
import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.exception.AuthorizationException;
import study.sharedcalendar.exception.NoMatchedUserException;
import study.sharedcalendar.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final UserMapper userMapper;
	private final UserConstant userConstant;
	private final EncryptionService encryptionService;
	private final HttpSession httpSession;

	public void login(LoginReq loginReq) {
		User user = userMapper.findLoginUser(loginReq);

		if (user == null) {
			throw new NoMatchedUserException(NO_MATCHING_USER_ID);
		}

		if (!encryptionService.isMatch(loginReq.getPassword(), user.getPassword())) {
			loginTryCountCheck(user.getTryCount());
			userMapper.incrementLoginTryCount(user.getId());
			throw new AuthorizationException(NO_MATCHING_USER_PASSWORD);
		}

		if (!user.isActivate()) {
			throw new AuthorizationException(INACTIVE_USER);
		}

		if (userMapper.getPasswordDateDiff(user.getId()) >= userConstant.MAX_PASSWORD_VALIDITY_PERIOD) {
			throw new AuthorizationException(EXCEEDED_PASSWORD_VALIDITY_PERIOD);
		}

		loginTryCountCheck(user.getTryCount());
		userMapper.initLoginTryCount(user.getId());
		setLoginSession(user.getId());
	}

	public void logout() {
		unsetLoginSession();
	}

	private void loginTryCountCheck(int count) {
		if (count == userConstant.MAX_LOGIN_TRY_COUNT) {
			throw new AuthorizationException(EXCEEDED_LOGIN_ATTEMPTS);
		}
	}

	public void setLoginSession(int id) {
		httpSession.setAttribute(userConstant.SESSION_ID, id);
	}

	public void unsetLoginSession() {
		httpSession.invalidate();
	}
}
