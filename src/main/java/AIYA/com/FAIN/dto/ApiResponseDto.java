package AIYA.com.FAIN.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class ApiResponseDto<T> {
  private boolean success;
  private T data;
  private String message;

  public ApiResponseDto(boolean success) {
    this.success = success;
  }

  public ApiResponseDto (boolean success, T data){
    this.success = success;
    this.data = data;
  }

  public ApiResponseDto (boolean success, String message){
    this.success = success;
    this.message = message;
  }

  public ApiResponseDto (boolean success, T data, String message){
    this.success = success;
    this.data = data;
    this.message = message;
  }

}
