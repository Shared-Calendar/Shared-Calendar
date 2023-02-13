package study.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(400, "파라미터 값을 확인해주세요."),

    /* 409 CONFLICT */
    ID_DUPLICATE(409, "중복된 아이디입니다");

    private final int status;
    private final String message;

}
