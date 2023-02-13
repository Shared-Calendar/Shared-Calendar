package study.sharedcalendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import study.constant.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity handleCustomException(CustomException ex) {
        return ResponseEntity.status(ex.getErrorCode().getStatus()).body(new ErrorResponse(ex.getErrorCode().getStatus(), ex.getErrorCode().getMessage()));
    }
}
