package AIYA.com.FAIN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CountResponseDto {
  private Integer fallCount;
  private Integer hCount;
  private Integer pCount;
}
