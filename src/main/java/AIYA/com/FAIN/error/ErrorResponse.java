package AIYA.com.FAIN.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
  private int status;
  private String message;

  public static ErrorResponse of(ErrorCode errorCode) {
    return new ErrorResponse(errorCode.getHttpStatus().value(), errorCode.getMessage());
  }
}
