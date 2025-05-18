package AIYA.com.FAIN.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupDto {
  private String userId;
  private String password;
  private String f_name;
  private String f_tel;
  private String name;
  private LocalDate birth;
  private String address;
  private String height;
  private String weight;
  private String bloodtype;
  private String medicine;
  private String hospital_name;
  private String disease;
  private String allergic;

}
