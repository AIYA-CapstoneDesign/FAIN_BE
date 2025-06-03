package AIYA.com.FAIN.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDto {
  //환자 기본정보
  private String name;
  private LocalDate birth;
  private String height;
  private String weight;
  private String medicine;
  private String disease;
  private String allergic;

  //리포트 관련정보
  private String situationImg;
  private LocalDateTime situationTime;

  //과거 리포트들
  private List<String> reportHistories;



}
