package study.sharedcalendar.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.sharedcalendar.dto.UserDTO;

@Mapper
public interface UserMapper {

    UserDTO getUser(String userId);
    void createUser(UserDTO user);
    int passwordChangedDate(int userID);
    void setLoginCount(@Param("id") int id, @Param("loginCount") int loginCount);
    void setActivate(@Param("id") int id, @Param("activate") boolean activate);
}
