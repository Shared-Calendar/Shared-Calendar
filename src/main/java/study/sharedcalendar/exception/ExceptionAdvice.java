package study.sharedcalendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(BindException.class)
    public ResponseEntity validationException(BindException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(ExceptionError.class)
    public ResponseEntity notFoundUSER(ExceptionError e){
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(e.getMessage());
    }


}
