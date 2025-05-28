package AIYA.com.FAIN.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  NOT_FOUND_USER(HttpStatus.NOT_FOUND,"존재하지 않는 유저입니다."),
  NOT_FOUND_REPORTS(HttpStatus.NOT_FOUND,"존재하지 않는 리포트입니다.");

  //Java enum은 필드선언을 나중에 해줘야함
  private final HttpStatus httpStatus;
  private final String message;

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public String getMessage() {
    return message;
  }
}
