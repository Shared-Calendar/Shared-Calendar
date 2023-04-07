package study.sharedcalendar.service;

import static study.sharedcalendar.constant.FcmConstant.*;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.HttpHeaders;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.dto.FcmMessage;
import study.sharedcalendar.dto.User;

@Service
@RequiredArgsConstructor
public class FcmService {
	private final UserService userService;
	private final ObjectMapper objectMapper;

	public void sendMessageTo(String targetToken, String title, String body) throws IOException {
		String message = makeMessage(targetToken, title, body);

		OkHttpClient client = new OkHttpClient();
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), message);
		Request request = new Request.Builder()
			.url(API_URL)
			.post(requestBody)
			.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
			.addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
			.build();

		client.newCall(request).execute();
	}

	public void sendLikeMessage(int sender_id, String targetToken) throws IOException {
		User sender = userService.findUserById(sender_id);
		String title = sender.getUserId() + "님이 회원님의 스케줄을 좋아합니다.";
		String body = "스케줄 : "; // 스케줄 조회 추가 예정

		sendMessageTo(targetToken, title, body);
	}

	public void sendAddConnectionMessage(int sender_id, String targetToken) throws IOException {
		User sender = userService.findUserById(sender_id);
		String title = sender.getUserId() + "님이 회원님의 친구가 되었습니다.";

		sendMessageTo(targetToken, title, "");
	}

	public void sendAddScheduleMessage(int sender_id, String targetToken) throws IOException {
		User sender = userService.findUserById(sender_id);
		String title = sender.getUserId() + "님이 회원님과의 스케줄을 추가했습니다.";
		String body = "추가한 스케줄 : "; // 추가한 스케줄 추가 예정

		sendMessageTo(targetToken, title, body);
	}

	public void sendEditScheduleMessage(int sender_id, String targetToken) throws IOException {
		User sender = userService.findUserById(sender_id);
		String title = sender.getUserId() + "님이 회원님과의 스케줄을 수정했습니다.";
		String body = "변경된 스케줄 : " + "->"; // 변경된 스케줄 추가 예정

		sendMessageTo(targetToken, title, body);
	}

	public void sendDeleteScheduleMessage(int sender_id, String targetToken) throws IOException {
		User sender = userService.findUserById(sender_id);
		String title = sender.getUserId() + "님이 회원님과의 스케줄을 삭제했습니다.";
		String body = "삭제된 스케줄 : "; // 삭제된 스케줄 추가 예정

		sendMessageTo(targetToken, title, body);
	}

	private String makeMessage(String targetToken, String title, String body) throws JsonProcessingException {
		FcmMessage fcmMessage = FcmMessage.builder()
			.message(FcmMessage.Message.builder()
				.token(targetToken)
				.notification(FcmMessage.Notification.builder()
					.title(title)
					.body(body)
					.image(null)
					.build()
				).build()).validateOnly(false).build();

		return objectMapper.writeValueAsString(fcmMessage);
	}

	private String getAccessToken() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials
			.fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream())
			.createScoped(Arrays.asList(FIREBASE_SCOPE));

		googleCredentials.refreshIfExpired();
		return googleCredentials.getAccessToken().getTokenValue();
	}
}