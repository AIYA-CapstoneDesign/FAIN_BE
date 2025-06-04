package AIYA.com.FAIN.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupDto {

  private String userId;
  private String password;
  @JsonProperty("fName")
  private String fName;
  @JsonProperty("fTel")
  private String fTel;
  private String name;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birth;
  private String address;
  private String height;
  private String weight;
  private String bloodtype;
  private String medicine;
  @JsonProperty("hospitalName")
  private String hospitalName;
  @JsonProperty("hospitalTel")
  private String hospitalTel;
  private String disease;
  private String allergic;
}
