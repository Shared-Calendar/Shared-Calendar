package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public boolean signUp(User user) {
        if (idCheck(user.getUserId())) {
            userMapper.createUser(user);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean idCheck(String userId) {
        if (userMapper.idCheck(userId) == 0)
            return true;
        else
            return false;
    }
}
