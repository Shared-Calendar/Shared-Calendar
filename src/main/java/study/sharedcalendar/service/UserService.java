package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.LoginRes;
import study.sharedcalendar.dto.SignUpReq;
import study.sharedcalendar.exception.AuthorizationException;
import study.sharedcalendar.exception.DuplicateException;
import study.sharedcalendar.mapper.UserMapper;

import static study.constant.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final EncryptionService encryptionService;
    private final SessionService sessionService;

    public void signUp(SignUpReq signUpReq) {
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

    public boolean userIdExist(String userId) {
        return userMapper.userIdExist(userId);
    }

    public void login(LoginReq loginReq) {
        LoginRes loginRes = userMapper.findLoginUser(loginReq);

        if (loginRes == null) {
            throw new NullPointerException("일치하는 아이디가 없습니다.");
        }
        if (!encryptionService.isMatch(loginReq.getPassword(), loginRes.getPassword())) {
            throw new AuthorizationException(NO_MATCHING_USER_PASSWORD);
        }

        sessionService.setLoginSession(loginReq.getUserId());
    }
}
