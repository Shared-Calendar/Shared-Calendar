package study.sharedcalendar.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.LoginRes;
import study.sharedcalendar.dto.SignUpReq;

@Mapper
public interface UserMapper {
    int createUser(SignUpReq signUpReq);

    boolean userIdExist(String userId);

    LoginRes findLoginUser(LoginReq loginReq);
}
