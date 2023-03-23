package study.sharedcalendar.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "calendar.config")
@ConstructorBinding
public class ScheduleConstant {
	public static String SCHEDULE_SESSION_ID;

	ScheduleConstant(String scheduleSessionId) {
		SCHEDULE_SESSION_ID = scheduleSessionId;
	}
}
