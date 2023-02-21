package study.sharedcalendar.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RuntimeExceptionVO extends RuntimeException{

    private final HttpStatus httpStatus;
    private final String message;


    public RuntimeExceptionVO(RuntimeExceptionCode runtimeExceptionCode){
        this.httpStatus = runtimeExceptionCode.getHttpStatus();
        this.message = runtimeExceptionCode.getMessage();
    }

}
