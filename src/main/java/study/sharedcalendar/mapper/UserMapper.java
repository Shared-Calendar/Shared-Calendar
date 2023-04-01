package study.sharedcalendar.mapper;

import org.apache.ibatis.annotations.Mapper;

import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.SignUpReq;
import study.sharedcalendar.dto.User;

@Mapper
public interface UserMapper {
	int createUser(SignUpReq signUpReq);

	boolean userIdExist(String userId);

	boolean emailExist(String email);

	User findLoginUser(LoginReq loginReq);

	void incrementLoginTryCount(int id);

	void initLoginTryCount(int id);

	int getPasswordDateDiff(int id);

	String findUserIdByEmail(String email);

	void resetPassword(String email, String password);

	boolean userExistById(int id);

	User findUserById(int id);
}
