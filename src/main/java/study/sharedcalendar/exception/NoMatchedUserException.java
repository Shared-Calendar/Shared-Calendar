package study.sharedcalendar.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import study.constant.ErrorCode;

@Getter
@RequiredArgsConstructor
public class NoMatchedUserException extends NullPointerException {
    private final ErrorCode errorCode;
}
