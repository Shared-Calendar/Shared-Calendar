package study.sharedcalendar.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "user.config")
@ConstructorBinding
public class LoginConstant {
    public static int MAX_LOGIN_TRY_COUNT;
    public static int MAX_PASSWORD_VALIDITY_PERIOD;
    public static String SESSION_ID;

    public LoginConstant(int maxLoginTryCount, int maxPasswordValidityPeriod, String sessionId) {
        MAX_LOGIN_TRY_COUNT = maxLoginTryCount;
        MAX_PASSWORD_VALIDITY_PERIOD = maxPasswordValidityPeriod;
        SESSION_ID = sessionId;
    }
}
