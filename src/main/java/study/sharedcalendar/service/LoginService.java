package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.sharedcalendar.constant.UserConstant;
import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.LoginRes;
import study.sharedcalendar.exception.AuthorizationException;
import study.sharedcalendar.exception.NoMatchedUserException;
import study.sharedcalendar.mapper.UserMapper;

import javax.servlet.http.HttpSession;

import static study.sharedcalendar.constant.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserMapper userMapper;
    private final UserConstant userConstant;
    private final EncryptionService encryptionService;
    private final HttpSession httpSession;

    public void login(LoginReq loginReq) {
        LoginRes loginRes = userMapper.findLoginUser(loginReq);

        if (loginRes == null || loginRes.getUserId() == null) {
            throw new NoMatchedUserException(NO_MATCHING_USER_ID);
        }
        if (!encryptionService.isMatch(loginReq.getPassword(), loginRes.getPassword())) {
            loginTryCountCheck(loginRes.getTryCount());
            userMapper.incrementLoginTryCount(loginRes.getId());
            throw new AuthorizationException(NO_MATCHING_USER_PASSWORD);
        }
        if (!loginRes.isActivate()) {
            throw new AuthorizationException(INACTIVE_USER);
        }
        if (userMapper.getPasswordDateDiff(loginRes.getId()) >= userConstant.getMaxPasswordValidityPeriod()) {
            throw new AuthorizationException(EXCEEDED_PASSWORD_VALIDITY_PERIOD);
        }
        loginTryCountCheck(loginRes.getTryCount());
        userMapper.initLoginTryCount(loginRes.getId());
        setLoginSession(loginRes.getId());
    }

    private void loginTryCountCheck(int count) {
        if (count == userConstant.getMaxLoginTryCount()) {
            throw new AuthorizationException(EXCEEDED_LOGIN_ATTEMPTS);
        }
    }

    public void setLoginSession(int id) {
        httpSession.setAttribute("id", id);
    }

    public void unsetLoginSession() {
        httpSession.invalidate();
    }
}
