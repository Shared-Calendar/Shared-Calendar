package study.sharedcalendar.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "connection.config")
@ConstructorBinding
public class ConnectionConstant {

	public static String USER_INVITE_URL;
	public static int USER_INVITE_CODE_SIZE;
	public static int CONNECTION_VIEW_EXPIRED_TIME;
	public static String CONNECTION_VIEW_REDIS_KEY;

	public ConnectionConstant(String userInviteUrl, int userInviteCodeSize,
		int connectionViewExpiredTime, String connectionViewRedisKey) {
		USER_INVITE_URL = userInviteUrl;
		USER_INVITE_CODE_SIZE = userInviteCodeSize;
		CONNECTION_VIEW_EXPIRED_TIME = connectionViewExpiredTime;
		CONNECTION_VIEW_REDIS_KEY = connectionViewRedisKey;
	}
}
