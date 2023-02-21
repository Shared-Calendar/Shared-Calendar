package study.sharedcalendar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.sharedcalendar.dto.UserDTO;

@Mapper
public interface UserMapper {

    UserDTO findUser(String userId);
    void inputUser(UserDTO user);
    int findPasswordChangedDate(int userID);
    void modifyLoginCount(@Param("id") int id, @Param("loginCount") int loginCount);
    void modifyActivate(@Param("id") int id, @Param("activate") boolean activate);

}
