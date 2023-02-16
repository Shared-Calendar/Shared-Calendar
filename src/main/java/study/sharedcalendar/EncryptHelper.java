package study.sharedcalendar;

import org.springframework.stereotype.Component;

@Component
public interface EncryptHelper {
    String encrypt(String password);

    boolean isMatch(String password, String hashed);
}
