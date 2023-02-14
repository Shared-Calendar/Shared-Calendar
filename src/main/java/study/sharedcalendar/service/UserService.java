package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.exception.DuplicateException;
import study.sharedcalendar.mapper.UserMapper;

import static study.constant.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void signUp(User user) {
        if (idExist(user.getUserId()))
            throw new DuplicateException(ID_DUPLICATE);
        else
            userMapper.createUser(user);
    }

    public boolean idExist(String userId) {
        return userMapper.idExist(userId);
    }
}
