package study.sharedcalendar.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "fcm.config")
@ConstructorBinding
public class FcmConstant {
	public static String FIREBASE_CONFIG_PATH;
	public static String FIREBASE_SCOPE;
	public static String API_URL;

	public FcmConstant(String firebaseConfigPath, String firebaseScope, String apiUrl) {
		FIREBASE_CONFIG_PATH = firebaseConfigPath;
		FIREBASE_SCOPE = firebaseScope;
		API_URL = apiUrl;
	}
}
