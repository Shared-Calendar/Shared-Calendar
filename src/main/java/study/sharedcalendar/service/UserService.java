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
        int isCheckedId = userMapper.idCheck(user.getUserId());
        if( isCheckedId == 0 ) {
            userMapper.createUser(user);
            return true;
        }
        else return false;
    }

    public int idCheck(String userId) {
        int result = userMapper.idCheck(userId);
        return result;
    }
}
