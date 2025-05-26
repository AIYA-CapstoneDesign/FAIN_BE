package AIYA.com.FAIN.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDetailResponseDto {
  private String name;
  private LocalDate birth;
  private String bloodtype;
  private String height;
  private String weight;
  private String disease;
  private String allergic;
  private String medicine;
}
