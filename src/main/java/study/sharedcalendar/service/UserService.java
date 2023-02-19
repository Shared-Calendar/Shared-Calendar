package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.sharedcalendar.dto.UserDTO;
import study.sharedcalendar.exception.ErrorCode;
import study.sharedcalendar.exception.ExceptionError;
import study.sharedcalendar.mapper.UserMapper;

import static org.mindrot.jbcrypt.BCrypt.*;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    private UserDTO getUser(UserDTO userInput){
        return userMapper.getUser(userInput.getUserId());
    }

    private void setLoginCount(int id, int count){
        userMapper.setLoginCount(id,count);
    }

    private void setActivate(int id, boolean state){
        userMapper.setActivate(id, state);
    }

    private String hashPassword(String inputPassword){
        return hashpw(inputPassword, gensalt());
    }

    private boolean checkPassword(String inputPassword, String hashedPassword){
        return checkpw(inputPassword, hashedPassword);
    }

    public void signUp(UserDTO user){
       if(getUser(user)!=null){
           throw new ExceptionError(ErrorCode.DUPLICATED_USER);
       }

        UserDTO signUpUser = UserDTO.builder()
                .userId(user.getUserId())
                .password(hashPassword(user.getPassword()))
                .email(user.getEmail())
                .inviteUrl(user.getInviteUrl())
                .build();

        userMapper.createUser(signUpUser);
    }

    public void signIn(UserDTO userInput){
        UserDTO userSaved = getUser(userInput);
        if(userSaved==null){
            throw new ExceptionError(ErrorCode.NOT_FOUND_USER);
        }

        if(!userSaved.isActivate()){
            throw new ExceptionError(ErrorCode.DEACTIVATED_USER);
        }

        int userLoginCount = userSaved.getLoginCount();
        if(checkPassword(userInput.getPassword(), userSaved.getPassword())){
            userInput = userSaved;
            if((userMapper.passwordChangedDate(userInput.getId()))>=90){
                throw new ExceptionError(ErrorCode.PASSWORD_EXPIRED);
            }

            setLoginCount(userSaved.getId(),0);
        }
        else{
            if(userLoginCount==4){
                setLoginCount(userSaved.getId(),0);
                setActivate(userSaved.getId(), false);
            }
            else{
                setLoginCount(userSaved.getId(),userLoginCount+1);
            }

            throw new ExceptionError(ErrorCode.DISAGREEMENT_PASSWORD);
        }
    }
}
