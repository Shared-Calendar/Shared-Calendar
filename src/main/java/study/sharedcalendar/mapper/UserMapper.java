package study.sharedcalendar.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.User;
import study.sharedcalendar.dto.SignUpReq;

@Mapper
public interface UserMapper {
    int createUser(SignUpReq signUpReq);

    boolean userIdExist(String userId);

    User findLoginUser(LoginReq loginReq);

    void incrementLoginTryCount(int id);

    void initLoginTryCount(int id);

    int getPasswordDateDiff(int id);
}
