package AIYA.com.FAIN.service;

import AIYA.com.FAIN.dto.GuardianDto;
import AIYA.com.FAIN.dto.MypageResponseDto;
import AIYA.com.FAIN.dto.PatientDto;
import AIYA.com.FAIN.entity.Users;
import AIYA.com.FAIN.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MypageService {

  private final UserRepository userRepository;

  public MypageService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public MypageResponseDto getMyPage(String userId){
    final Users user = userRepository
        .findByUserId(userId).orElseThrow(()->{System.out.println("userId 못 찾음: '" + userId + "'");
    return new ResponseStatusException(HttpStatus.FORBIDDEN, "유저 없음");
  });

    //guardiandto에서 필요한 정보만 user에서 mapping
    GuardianDto guardianDto = new GuardianDto(
        user.getUserId(),
        user.getFName(),
        user.getFTel()
    );

    PatientDto patientDto = new PatientDto(
        user.getName(),
        user.getBirth(),
        user.getAddress(),
        user.getHeight(),
        user.getWeight(),
        user.getBloodtype(),
        user.getDisease(),
        user.getHospitalName()
    );

    return new MypageResponseDto(guardianDto, patientDto);
  }


}
