package study.sharedcalendar.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import study.sharedcalendar.constant.ErrorCode;

@Getter
@RequiredArgsConstructor
public class DuplicateException extends IllegalArgumentException {
	private final ErrorCode errorCode;
}