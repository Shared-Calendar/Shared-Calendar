package study.sharedcalendar.service;

import org.mindrot.jbcrypt.BCrypt;
import study.sharedcalendar.EncryptHelper;

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
