package study.sharedcalendar.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RuntimeExceptionVO extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;


    public RuntimeExceptionVO(RuntimeExceptionCode runtimeExceptionCode){
        this.httpStatus = runtimeExceptionCode.getHttpStatus();
        this.message = runtimeExceptionCode.getMessage();
    }

}
