package AIYA.com.FAIN.service;

import AIYA.com.FAIN.dto.GuardianDto;
import AIYA.com.FAIN.dto.MypageResponseDto;
import AIYA.com.FAIN.dto.MypageUpdateRequestDto;
import AIYA.com.FAIN.dto.PatientDto;
import AIYA.com.FAIN.entity.Users;
import AIYA.com.FAIN.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MypageService {

  private final UserRepository userRepository;

  public MypageService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
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

  @Transactional
  public void UpdateMypage(String userId, MypageUpdateRequestDto dto){
    Users users =  userRepository.findByUserId(userId)
        .orElseThrow(()-> new RuntimeException("사용자를 찾을 수 없음"));

    if (dto.getFName() != null) {
      users.setFName(dto.getFName());
    }
    if (dto.getFTel() != null) {
      users.setFTel(dto.getFTel());
    }
    if(dto.getName() != null) {
      users.setName(dto.getName());
    }
    if(dto.getBirth() != null) {
      users.setBirth(dto.getBirth());
    }
    if(dto.getAddress() != null) {
      users.setAddress(dto.getAddress());
    }
    if(dto.getHeight() != null) {
      users.setHeight(dto.getHeight());
    }
    if(dto.getWeight() != null) {
      users.setWeight(dto.getWeight());
    }
    if(dto.getBloodType() != null) {
      users.setBloodtype(dto.getBloodType());
    }
    if(dto.getAllergic() != null) {
      users.setAllergic(dto.getAllergic());
    }
    if(dto.getMedicine() != null) {
      users.setMedicine(dto.getMedicine());
    }
    if(dto.getHospitalName() != null) {
      users.setHospitalName(dto.getHospitalName());
    }
  }


}
