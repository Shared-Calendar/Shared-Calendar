package study.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파라미터 값을 확인해주세요."),

    /* 404 NOT_FOUND */
    NO_MATCHING_USER_PASSWORD(HttpStatus.NOT_FOUND, "비밀번호가 일치하지 않습니다."),

    /* 409 CONFLICT */
    ID_DUPLICATE(HttpStatus.CONFLICT, "중복된 아이디입니다");

    private final HttpStatus status;
    private final String message;
}
