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
        String password = user.getPassword();
        passwordValidation(password);

        String encryptedPassword = encryptionService.encrypt(password);
        User signUpUser = User.builder()
                .userId(user.getUserId())
                .password(encryptedPassword)
                .email(user.getEmail())
                .inviteUrl(user.getInviteUrl())
                .build();

        userMapper.createUser(signUpUser);
    }

    public void userIdDuplicationCheck(String userId) {
        userIdValidation(userId);

        if (userIdExist(userId)) {
            throw new DuplicateException(ID_DUPLICATE);
        }
    }

    public boolean userIdExist(String userId) {
        return userMapper.userIdExist(userId);
    }

    public void passwordValidation(String password) {
        String passwordPolicy = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,15}$";

        Pattern pattern = Pattern.compile(passwordPolicy);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new ValidationException("잘못된 비밀번호 형식입니다.");
        }
    }

    public void userIdValidation(String userId) {
        String userIdPolicy = "^[0-9a-zA-Z]{3,10}$";

        Pattern pattern = Pattern.compile(userIdPolicy);
        Matcher matcher = pattern.matcher(userId);

        if (!matcher.matches()) {
            throw new ValidationException("잘못된 아이디 형식입니다.");
        }
    }
}
