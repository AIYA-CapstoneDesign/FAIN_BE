package AIYA.com.FAIN.error;

import AIYA.com.FAIN.error.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
    ErrorCode errorCode = ex.getErrorCode();
    return new ResponseEntity<>(ErrorResponse.of(errorCode), errorCode.getHttpStatus());
  }

}
