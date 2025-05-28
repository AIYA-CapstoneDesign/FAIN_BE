package AIYA.com.FAIN.error;

import AIYA.com.FAIN.dto.ApiResponseDto;
import AIYA.com.FAIN.error.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ApiResponseDto<Void>> handleCustomException(CustomException ex){
    ErrorCode errorCode = ex.getErrorCode();
    return ResponseEntity
        .status(errorCode.getHttpStatus())
        .body(ApiResponseDto.error(errorCode.getMessage()));
  }
  // (선택) 예기치 못한 에러 처리
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponseDto<Void>> handleUnhandled(Exception ex) {
    ex.printStackTrace(); // or log
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ApiResponseDto.error("예기치 않은 서버 오류가 발생했습니다."));
  }

}
