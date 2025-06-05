package AIYA.com.FAIN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class MonthlyResponseDto {
  private String aiComment;
}
