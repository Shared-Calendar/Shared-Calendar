package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import study.sharedcalendar.dto.UserDTO;
import study.sharedcalendar.exception.RuntimeExceptionVO;
import study.sharedcalendar.mapper.UserMapper;

import static org.mindrot.jbcrypt.BCrypt.*;
import static study.sharedcalendar.exception.RuntimeExceptionCode.*;


@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${PASSWORD_EXPIRED_DATE}")
    private int PASSWORD_EXPIRED_DATE;

    private final UserMapper userMapper;


    private UserDTO findUser(UserDTO userInput){
        return userMapper.findUser(userInput.getUserId());
    }

    private void modifyLoginCount(int id, int count){
        userMapper.modifyLoginCount(id,count);
    }

    private void modifyActivate(int id, boolean state){
        userMapper.modifyActivate(id, state);
    }

    private String hashPassword(String inputPassword){
        return hashpw(inputPassword, gensalt());
    }

    private boolean checkPassword(String inputPassword, String hashedPassword){
        return checkpw(inputPassword, hashedPassword);
    }


    public void signUp(UserDTO user){
       if(findUser(user)!=null){
           throw new RuntimeExceptionVO(DUPLICATED_USER);
       }

        UserDTO signUpUser = UserDTO.builder()
                .userId(user.getUserId())
                .password(hashPassword(user.getPassword()))
                .email(user.getEmail())
                .inviteUrl(user.getInviteUrl())
                .build();

        userMapper.inputUser(signUpUser);
    }

    public void signIn(UserDTO userInput){
        UserDTO userSaved = findUser(userInput);
        if(userSaved==null){
            throw new RuntimeExceptionVO(NOT_FOUND_USER);
        }

        if(!userSaved.isActivate()){
            throw new RuntimeExceptionVO(DEACTIVATED_USER);
        }

        int userLoginCount = userSaved.getLoginCount();
        if(checkPassword(userInput.getPassword(), userSaved.getPassword())){
            userInput = userSaved;
            if((userMapper.findPasswordChangedDate(userInput.getId()))>=PASSWORD_EXPIRED_DATE){
                throw new RuntimeExceptionVO(PASSWORD_EXPIRED);
            }

            modifyLoginCount(userSaved.getId(),0);
        } else{
            if(userLoginCount==4){
                modifyLoginCount(userSaved.getId(),0);
                modifyActivate(userSaved.getId(), false);
            } else{
                modifyLoginCount(userSaved.getId(),userLoginCount+1);
            }

            throw new RuntimeExceptionVO(DISAGREEMENT_PASSWORD);
        }
    }

}
