package study.sharedcalendar.mapper;

import org.apache.ibatis.annotations.Mapper;

import study.sharedcalendar.dto.LoginReq;
import study.sharedcalendar.dto.User;

@Mapper
public interface LoginMapper {

	User findLoginUser(LoginReq loginReq);

	void incrementLoginTryCount(int id);

	void initLoginTryCount(int id);

	int getPasswordDateDiff(int id);

}
