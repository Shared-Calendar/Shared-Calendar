package study.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파라미터 값을 확인해주세요."),

    /* 409 CONFLICT */
    ID_DUPLICATE(HttpStatus.CONFLICT, "중복된 아이디입니다");

    private final HttpStatus status;
    private final String message;
}
