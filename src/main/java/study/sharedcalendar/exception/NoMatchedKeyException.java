package study.sharedcalendar.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import study.sharedcalendar.constant.ErrorCode;

@Getter
@RequiredArgsConstructor
public class NoMatchedKeyException extends RuntimeException {
	private final ErrorCode errorCode;
}
