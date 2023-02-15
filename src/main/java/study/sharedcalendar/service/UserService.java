package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.sharedcalendar.EncryptHelper;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.exception.DuplicateException;
import study.sharedcalendar.mapper.UserMapper;

import static study.constant.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final EncryptService encryptService;

    public void signUp(User user) {
        if (userIdExist(user.getUserId()))
            throw new DuplicateException(ID_DUPLICATE);

        String encryptedPassword = encryptService.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);

        userMapper.createUser(user);
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
