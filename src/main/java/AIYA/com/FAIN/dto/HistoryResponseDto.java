package AIYA.com.FAIN.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryResponseDto {

  private String reportId;
  private String situationTime;
  private String actionType;

}
