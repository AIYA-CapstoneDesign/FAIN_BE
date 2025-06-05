package AIYA.com.FAIN.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class MonthlyRequestDto {
  //환자 기본정보
  private String name;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate birth;
  private String height;
  private String weight;
  private String medicine;
  private String disease;
  private String allergic;

  private Integer fallCount;
  private Integer hCount;
  private Integer pCount;

  //과거 해당 년 월의 리포트들
  private List<String> monthlyReportHistories;
}
