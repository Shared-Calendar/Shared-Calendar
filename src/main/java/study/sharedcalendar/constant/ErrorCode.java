package study.sharedcalendar.constant;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파라미터 값을 확인해주세요."),
	FCM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "FCM에 예기치 않은 에러가 발생했습니다."),

	/* 400 BAD REQUEST */
	NO_MATCHING_AUTH_CODE(BAD_REQUEST, "인증코드가 일치하지 않습니다."),
	/* 403 FORBIDDEN */
	INACTIVE_USER(FORBIDDEN, "휴면 계정입니다."),
	EXCEEDED_LOGIN_ATTEMPTS(FORBIDDEN, "로그인 횟수가 초과했습니다. 비밀번호를 재설정해주세요."),
	EXCEEDED_PASSWORD_VALIDITY_PERIOD(FORBIDDEN, "비밀번호 변경 주기가 지났습니다. 비밀번호를 재설정해주세요."),
	NOT_AUTHENTIC_EMAIL(FORBIDDEN, "인증된 이메일이 아닙니다."),
	/* 404 NOT_FOUND */
	NO_MATCHING_USER_ID(NOT_FOUND, "아이디와 일치하는 유저가 없습니다."),
	NO_MATCHING_USER_PASSWORD(NOT_FOUND, "비밀번호가 일치하지 않습니다."),
	NO_MATCHING_USER_BY_EMAIL(NOT_FOUND, "해당 이메일로 가입된 유저가 없습니다."),
	NO_LOGIN_INFORMATION(NOT_FOUND, "로그인 상태가 아닙니다."),
	NO_MATCHING_USER_BY_ID(NOT_FOUND, "해당 유저는 존재하지 않습니다."),

	/* 409 CONFLICT */
	ID_DUPLICATE(CONFLICT, "중복된 아이디입니다."),
	EMAIL_DUPLICATE(CONFLICT, "중복된 이메일입니다."),

	/* 503 SERVICE_UNAVAILABLE */
	GET_LOCK_FAILED(SERVICE_UNAVAILABLE, "락을 획득하지 못했습니다."),
	CREATE_LIKE_FAILED(SERVICE_UNAVAILABLE, "좋아요 레코드를 생성하는데 실패했습니다."),
	UPDATE_LIKE_FAILED(SERVICE_UNAVAILABLE, "좋아요를 추가하는 데 실패했습니다."),
	UPDATE_UNLIKE_FAILED(SERVICE_UNAVAILABLE, "좋아요를 취소하는 데 실패했습니다.");

	private final HttpStatus status;
	private final String message;
}
