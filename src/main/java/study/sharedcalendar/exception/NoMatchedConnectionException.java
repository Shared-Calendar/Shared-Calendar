package study.sharedcalendar.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import study.sharedcalendar.constant.ErrorCode;

@Getter
@RequiredArgsConstructor
public class NoMatchedConnectionException extends NullPointerException {
	private final ErrorCode errorCode;
}