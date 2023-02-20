package study.sharedcalendar.constant;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "user.config")
@ConstructorBinding
public class UserConstant {
    private final int maxLoginTryCount;
    private final int maxPasswordValidityPeriod;

    public UserConstant(int maxLoginTryCount, int maxPasswordValidityPeriod) {
        this.maxLoginTryCount = maxLoginTryCount;
        this.maxPasswordValidityPeriod = maxPasswordValidityPeriod;
    }
}
