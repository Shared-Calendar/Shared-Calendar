package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.exception.CustomException;
import study.sharedcalendar.mapper.UserMapper;

import static study.constant.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void signUp(User user) {
        idCheck(user.getUserId());

        try {
            userMapper.createUser(user);
        } catch (Exception e) {
            throw new CustomException(INTERNAL_SERVER_ERROR);
        }
    }

    public void idCheck(String userId) {
        if (userMapper.idCheck(userId) != 0)
            throw new CustomException(ID_DUPLICATE);
    }
}
