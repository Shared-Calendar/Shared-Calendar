package study.sharedcalendar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.SignUpReq;
import study.sharedcalendar.dto.User;

@Mapper
public interface UserMapper {
	int createUser(SignUpReq signUpReq);

	boolean userIdExist(String userId);

	User findLoginUser(LoginReq loginReq);

	void incrementLoginTryCount(int id);

	void initLoginTryCount(int id);

	int getPasswordDateDiff(int id);

	Integer getIdByInviteCode(String inviteUrlCode);

	void modifyInviteUrlCode(@Param("id") int id, @Param("inviteUrlCode") String inviteUrlCode);
}