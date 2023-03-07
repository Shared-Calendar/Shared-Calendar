package study.sharedcalendar.constant;

import static org.springframework.http.HttpStatus.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파라미터 값을 확인해주세요."),

	/* 400 BAD REQUEST */
	NO_MATCHING_AUTH_CODE(BAD_REQUEST, "인증코드가 일치하지 않습니다."),
	/* 403 FORBIDDEN */
	INACTIVE_USER(FORBIDDEN, "휴면 계정입니다."),
	EXCEEDED_LOGIN_ATTEMPTS(FORBIDDEN, "로그인 횟수가 초과했습니다. 비밀번호를 재설정해주세요."),
	EXCEEDED_PASSWORD_VALIDITY_PERIOD(FORBIDDEN, "비밀번호 변경 주기가 지났습니다. 비밀번호를 재설정해주세요."),
	/* 404 NOT_FOUND */
	NO_MATCHING_USER_ID(NOT_FOUND, "아이디와 일치하는 유저가 없습니다."),
	NO_MATCHING_USER_PASSWORD(NOT_FOUND, "비밀번호가 일치하지 않습니다."),
    NO_LOGIN_INFORMATION(NOT_FOUND, "로그인 상태가 아닙니다."),

	/* 409 CONFLICT */
	ID_DUPLICATE(CONFLICT, "중복된 아이디입니다");

    private final HttpStatus status;
    private final String message;
}
