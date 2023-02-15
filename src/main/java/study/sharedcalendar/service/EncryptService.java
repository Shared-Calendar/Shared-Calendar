package study.sharedcalendar.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;
import study.sharedcalendar.EncryptHelper;

@Component
public class EncryptService implements EncryptHelper {
    @Override
    public String encrypt(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean isMatch(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
