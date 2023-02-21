package study.sharedcalendar.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "user.config")
@ConstructorBinding
public class UserConstant {
    public static int MAX_LONG_TRY_COUNT;
    public static int MAX_PASSWORD_VALIDITY_PERIOD;

    public UserConstant(int maxLoginTryCount, int maxPasswordValidityPeriod) {
        MAX_LONG_TRY_COUNT = maxLoginTryCount;
        MAX_PASSWORD_VALIDITY_PERIOD = maxPasswordValidityPeriod;
    }
}
