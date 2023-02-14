package study.sharedcalendar.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.sharedcalendar.dto.User;

@Mapper
public interface UserMapper {
    int createUser(User user);

    boolean userIdExist(String userId);

}
