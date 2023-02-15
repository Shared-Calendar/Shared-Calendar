package study.sharedcalendar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.sharedcalendar.service.EncryptService;

@Configuration
public class AppConfig {

    @Bean
    public EncryptHelper encryptHelper() {
        return new EncryptService();
    }
}
