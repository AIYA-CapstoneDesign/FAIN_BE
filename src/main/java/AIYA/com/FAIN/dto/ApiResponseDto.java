package AIYA.com.FAIN.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponseDto<T> {
  private boolean success;
  private T data;
  private String message;
  //성공 응답 (data만)
  public static <T> ApiResponseDto<T> success(T data) {
    return new ApiResponseDto<>(true, data, null);
  }

  //성공 응답 (message만)
  public static ApiResponseDto<Void> successMessage(String message) {
    return new ApiResponseDto<>(true, null, message);
  }

  //실패 응답 (message만)
  public static ApiResponseDto<Void> error(String message) {
    return new ApiResponseDto<>(false, null, message);
  }

}
