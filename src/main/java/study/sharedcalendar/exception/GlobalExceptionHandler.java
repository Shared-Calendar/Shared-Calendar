package study.sharedcalendar.exception;

import static org.springframework.http.HttpStatus.*;

import javax.mail.MessagingException;
import javax.validation.ConstraintViolationException;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import study.sharedcalendar.constant.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateException.class)
	protected ResponseEntity handleDuplicateException(DuplicateException ex) {
		return ResponseEntity
			.status(ex.getErrorCode().getStatus())
			.body(new ErrorResponse(ex.getErrorCode().getStatus(), ex.getErrorCode().getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleMethodValidException(MethodArgumentNotValidException ex) {
		return ResponseEntity
			.status(BAD_REQUEST)
			.body(new ErrorResponse(BAD_REQUEST, ex.getFieldError().getDefaultMessage()));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseEntity
			.status(BAD_REQUEST)
			.body(new ErrorResponse(BAD_REQUEST, ex.getMessage()));
	}

	@ExceptionHandler(NoMatchedUserException.class)
	public ResponseEntity handleNullPointerException(NoMatchedUserException ex) {
		return ResponseEntity
			.status(ex.getErrorCode().getStatus())
			.body(new ErrorResponse(ex.getErrorCode().getStatus(), ex.getErrorCode().getMessage()));
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity handleAuthorizationException(AuthorizationException ex) {
		return ResponseEntity
			.status(ex.getErrorCode().getStatus())
			.body(new ErrorResponse(ex.getErrorCode().getStatus(), ex.getErrorCode().getMessage()));
	}

	@ExceptionHandler(MailSendException.class)
	public ResponseEntity handleMailSendException(MailSendException ex) {
		return ResponseEntity
			.status(NOT_IMPLEMENTED)
			.body(new ErrorResponse(NOT_IMPLEMENTED, "인증 메일 전송에 실패했습니다."));
	}

	@ExceptionHandler(MessagingException.class)
	public ResponseEntity handleMessagingException(MessagingException ex) {
		return ResponseEntity
			.status(NOT_IMPLEMENTED)
			.body(new ErrorResponse(NOT_IMPLEMENTED, "인증 메일 설정값이 잘못되었습니다."));
	}

	@ExceptionHandler(NoMatchedKeyException.class)
	public ResponseEntity handleNoMatchedKeyException(NoMatchedKeyException ex) {
		return ResponseEntity
			.status(ex.getErrorCode().getStatus())
			.body(new ErrorResponse(ex.getErrorCode().getStatus(), ex.getErrorCode().getMessage()));
	}

	@ExceptionHandler(ThreadException.class)
	public ResponseEntity handleInterruptedException(ThreadException ex) {
		return ResponseEntity
			.status(ex.getErrorCode().getStatus())
			.body(new ErrorResponse(ex.getErrorCode().getStatus(), ex.getErrorCode().getMessage()));
	}

	@ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
	public ResponseEntity handleJdbcSQLIntegrityConstraintViolationException(
		JdbcSQLIntegrityConstraintViolationException ex) {
		return ResponseEntity
			.status(BAD_REQUEST)
			.body(new ErrorResponse(BAD_REQUEST, "일치하는 정보가 DB에 없습니다."));
	}
}
