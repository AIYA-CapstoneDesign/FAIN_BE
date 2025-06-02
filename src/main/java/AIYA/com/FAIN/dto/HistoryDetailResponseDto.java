package AIYA.com.FAIN.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryDetailResponseDto {

  private String name;
  private String situationTime;
  private String situationImg;
  private String report;
  private String actionType;

}
