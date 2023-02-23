package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.LoginRes;
import study.sharedcalendar.exception.AuthorizationException;
import study.sharedcalendar.exception.NoMatchedUserException;
import study.sharedcalendar.mapper.UserMapper;

import javax.servlet.http.HttpSession;

import static study.constant.ErrorCode.NO_MATCHING_USER_ID;
import static study.constant.ErrorCode.NO_MATCHING_USER_PASSWORD;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserMapper userMapper;
    private final EncryptionService encryptionService;
    private final HttpSession httpSession;

    public void login(LoginReq loginReq) {
        LoginRes loginRes = userMapper.findLoginUser(loginReq);

        if (loginRes == null) {
            throw new NoMatchedUserException(NO_MATCHING_USER_ID);
        }
        if (!encryptionService.isMatch(loginReq.getPassword(), loginRes.getPassword())) {
            throw new AuthorizationException(NO_MATCHING_USER_PASSWORD);
        }

        setLoginSession(loginRes.getId());
    }

    public void setLoginSession(int id) {
        httpSession.setAttribute("id", id);
    }

    public void unsetLoginSession() {
        httpSession.invalidate();
    }
}
