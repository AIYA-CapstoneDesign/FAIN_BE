package AIYA.com.FAIN.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class MypageUpdateRequestDto {

  // 보호자 정보수정
  @JsonProperty("fName")
  private String fName;
  @JsonProperty("fTel")
  private String fTel;

  // 환자 정보 수정
  private String name;
  private LocalDate birth;
  private String address;
  private String height;
  private String weight;
  private String bloodType;
  private String disease;
  private String allergic;
  private String medicine;
  @JsonProperty("hospitalName")
  private String hospitalName;
  @JsonProperty("hospitalTel")
  private String hospitalTel;


}
