package study.sharedcalendar.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RuntimeExceptionCode {

    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "NON_EXISTENT_USER"),
    DUPLICATED_USER(HttpStatus.BAD_REQUEST, "ID_DUPLICATE"),
    DISAGREEMENT_PASSWORD(HttpStatus.BAD_REQUEST, "PASSWORD_MISMATCH"),
    DEACTIVATED_USER(HttpStatus.BAD_REQUEST, "DEACTIVATED_USER"),
    PASSWORD_EXPIRED(HttpStatus.BAD_REQUEST,"PASSWORD_EXPIRED"),
    NO_LOGIN_INFORMATION(HttpStatus.NOT_FOUND, "NO_LOGIN_INFORMATION");


    private final HttpStatus httpStatus;
    private final String Message;

}
