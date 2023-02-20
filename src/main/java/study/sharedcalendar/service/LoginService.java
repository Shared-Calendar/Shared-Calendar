package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.sharedcalendar.constant.UserConstant;
import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.LoginRes;
import study.sharedcalendar.exception.AuthorizationException;
import study.sharedcalendar.exception.NoMatchedUserException;
import study.sharedcalendar.mapper.UserMapper;

import javax.servlet.http.HttpSession;

import static study.sharedcalendar.constant.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserMapper userMapper;
    private final UserConstant userConstant;
    private final EncryptionService encryptionService;
    private final HttpSession httpSession;

    public void login(LoginReq loginReq) {
        LoginRes loginRes = userMapper.findLoginUser(loginReq);

        if (loginRes.getUserId() == null) {
            throw new NoMatchedUserException(NO_MATCHING_USER_ID);
        }
        if (!encryptionService.isMatch(loginReq.getPassword(), loginRes.getPassword())) {
            if (loginRes.getTryCount() == userConstant.getMaxLoginTryCount()) {
                throw new AuthorizationException(EXCEEDED_LOGIN_ATTEMPTS);
            }
            userMapper.incrementLoginTryCount(loginRes.getId());
            throw new AuthorizationException(NO_MATCHING_USER_PASSWORD);
        }
        if (!loginRes.isActivate()) {
            throw new AuthorizationException(INACTIVE_USER);
        }
        userMapper.initLoginTryCount(loginRes.getId());
        setLoginSession(loginRes.getId());
    }

    public void setLoginSession(int id) {
        httpSession.setAttribute("id", id);
    }

    public void unsetLoginSession() {
        httpSession.invalidate();
    }
}
