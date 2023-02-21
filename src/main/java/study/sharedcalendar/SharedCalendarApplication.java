package study.sharedcalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class SharedCalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharedCalendarApplication.class, args);
	}

}
