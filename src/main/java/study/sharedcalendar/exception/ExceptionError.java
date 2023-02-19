package study.sharedcalendar.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionError extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public ExceptionError(ErrorCode errorCode){
        this.httpStatus = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }
}
