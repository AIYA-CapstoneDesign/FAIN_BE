package AIYA.com.FAIN.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatientDto {

  private String name;
  private LocalDate birth;
  private String address;
  private String height;
  private String weight;
  private String bloodtype;
  private String disease;
  private String hospitalName;

}
