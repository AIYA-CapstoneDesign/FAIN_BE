package AIYA.com.FAIN.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MypageResponseDto {
  private GuardianDto guardian;
  private PatientDto patient;

}
