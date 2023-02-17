package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.exception.DuplicateException;
import study.sharedcalendar.mapper.UserMapper;

import javax.validation.ValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static study.constant.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final EncryptionService encryptionService;

    public void signUp(User user) {
        String encryptedPassword = encryptionService.encrypt(user.getPassword());
        User signUpUser = User.builder()
                .userId(user.getUserId())
                .password(encryptedPassword)
                .email(user.getEmail())
                .inviteUrl(user.getInviteUrl())
                .build();

        userMapper.createUser(signUpUser);
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
