package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.sharedcalendar.constant.UserConstant;
import study.sharedcalendar.dto.SignUpReq;
import study.sharedcalendar.exception.DuplicateException;
import study.sharedcalendar.mapper.UserMapper;

import static study.sharedcalendar.constant.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final EncryptionService encryptionService;

    public void signUp(SignUpReq signUpReq) {
        if (userIdExist(signUpReq.getUserId())) {
            throw new DuplicateException(ID_DUPLICATE);
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

    public boolean userIdExist(String userId) {
        return userMapper.userIdExist(userId);
    }

}
