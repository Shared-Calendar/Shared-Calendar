package study.sharedcalendar.service;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import study.sharedcalendar.dto.UserDTO;
import study.sharedcalendar.mapper.UserMapper;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    private UserDTO getUser(UserDTO userInput){
        return userMapper.getUser(userInput.getUserId());
    }

    private String hashPassword(String inputPassword){
        return BCrypt.hashpw(inputPassword, BCrypt.gensalt());
    }

    private boolean checkPassword(String inputPassword, String hashedPassword){
        return BCrypt.checkpw(inputPassword, hashedPassword);
    }

    public void signUp(UserDTO user){
       if(getUser(user)!=null){
           throw new DuplicateRequestException("ID_DUPLICATE");
       }
        UserDTO signUpUser = UserDTO.builder()
                .userId(user.getUserId())
                .password(hashPassword(user.getPassword()))
                .email(user.getEmail())
                .inviteUrl(user.getInviteUrl())
                .build();
        userMapper.createUser(signUpUser);
    }
}
