package study.sharedcalendar.mapper;
import org.apache.ibatis.annotations.Mapper;
import study.sharedcalendar.dto.UserDTO;

@Mapper
public interface UserMapper {

    UserDTO getUser(String userId);
    void createUser(UserDTO user);
}
