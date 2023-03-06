package study.sharedcalendar.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "user.config")
@ConstructorBinding
public class UserConstant {
	public static int MAX_LOGIN_TRY_COUNT;
	public static int MAX_PASSWORD_VALIDITY_PERIOD;
	public static String LOGIN_SESSION_ID;
	public static String USER_INVITE_URL;
	public static int USER_INVITE_CODE_SIZE;

	public UserConstant(int maxLoginTryCount, int maxPasswordValidityPeriod, String loginSessionId,
		String userInviteUrl, int userInviteCodeSize) {
		MAX_LOGIN_TRY_COUNT = maxLoginTryCount;
		MAX_PASSWORD_VALIDITY_PERIOD = maxPasswordValidityPeriod;
		LOGIN_SESSION_ID = loginSessionId;
		USER_INVITE_URL = userInviteUrl;
		USER_INVITE_CODE_SIZE = userInviteCodeSize;
	}
}