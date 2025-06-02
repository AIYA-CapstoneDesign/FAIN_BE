package AIYA.com.FAIN.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GuardianDto {
  private String userId;
  @JsonProperty("fName")
  private String fName;
  @JsonProperty("fTel")
  private String fTel;
}
