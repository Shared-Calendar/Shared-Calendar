package study.sharedcalendar.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "user.config")
public class UserConstant {
    private int maxLoginTryCount;
    private int maxPasswordValidityPeriod;
}
