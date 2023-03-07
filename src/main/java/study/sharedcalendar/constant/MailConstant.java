package study.sharedcalendar.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "mail.config")
@ConstructorBinding
public class MailConstant {
	public static long EXPIRE_TIME;
	public static int AUTH_CODE_LENGTH;

	public MailConstant(long expireTime, int authCodeLength) {
		EXPIRE_TIME = expireTime;
		AUTH_CODE_LENGTH = authCodeLength;
	}
}
