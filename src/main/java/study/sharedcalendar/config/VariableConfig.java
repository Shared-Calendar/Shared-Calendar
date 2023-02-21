package study.sharedcalendar.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties
@ConstructorBinding
@Getter
@RequiredArgsConstructor
public class VariableConfig {

    private final int passwordExpiredDate;

    private final int maxLoginCount;

}
