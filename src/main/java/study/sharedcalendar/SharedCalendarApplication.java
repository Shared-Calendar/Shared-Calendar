package study.sharedcalendar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import study.sharedcalendar.constant.UserConstant;


@MapperScan
@EnableConfigurationProperties(UserConstant.class)
@SpringBootApplication
public class SharedCalendarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharedCalendarApplication.class, args);
    }

}
