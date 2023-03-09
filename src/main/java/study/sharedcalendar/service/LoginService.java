package study.sharedcalendar.service;

import static study.sharedcalendar.constant.ErrorCode.*;
import static study.sharedcalendar.constant.LoginConstant.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.exception.AuthorizationException;
import study.sharedcalendar.exception.NoMatchedUserException;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final UserService userService;
	private final EncryptionService encryptionService;
	private final HttpSession httpSession;

	public void login(LoginReq loginReq) {
		User user = userService.findLoginUser(loginReq);

		if (user == null) {
			throw new NoMatchedUserException(NO_MATCHING_USER_ID);
		}

		if (!encryptionService.isMatch(loginReq.getPassword(), user.getPassword())) {
			loginTryCountCheck(user.getTryCount());
			userService.incrementLoginTryCount(user.getId());
			throw new AuthorizationException(NO_MATCHING_USER_PASSWORD);
		}

		if (!user.isActivate()) {
			throw new AuthorizationException(INACTIVE_USER);
		}

		if (userService.getPasswordDateDiff(user.getId()) >= MAX_PASSWORD_VALIDITY_PERIOD) {
			throw new AuthorizationException(EXCEEDED_PASSWORD_VALIDITY_PERIOD);
		}

		loginTryCountCheck(user.getTryCount());
		userService.initLoginTryCount(user.getId());
		setLoginSession(user.getId());
	}

	public void logout() {
		httpSession.invalidate();
	}

	private void loginTryCountCheck(int count) {
		if (count == MAX_LOGIN_TRY_COUNT) {
			throw new AuthorizationException(EXCEEDED_LOGIN_ATTEMPTS);
		}
	}

	public void setLoginSession(int id) {
		httpSession.setAttribute(SESSION_ID, id);
	}
}
