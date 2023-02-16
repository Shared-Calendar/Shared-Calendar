package study.sharedcalendar.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import study.sharedcalendar.EncryptHelper;

@Service
@Primary
public class EncryptByBcryptService implements EncryptHelper {
    @Override
    public String encrypt(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean isMatch(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
