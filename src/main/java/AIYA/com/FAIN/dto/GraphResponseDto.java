package AIYA.com.FAIN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class GraphResponseDto {
  private Integer dawn;
  private Integer morning;
  private Integer afternoon;
  private Integer night;

}
