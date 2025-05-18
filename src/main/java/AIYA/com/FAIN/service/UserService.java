package AIYA.com.FAIN.service;

import AIYA.com.FAIN.dto.UserSignupDto;
import AIYA.com.FAIN.entity.Users;
import AIYA.com.FAIN.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  @Autowired
  private final UserRepository userRepository;

  public void signup(UserSignupDto dto){
    if (userRepository.existsByUserId(dto.getUserId())) {
      throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
    }
    Users user = new Users(
        dto.getUserId(),
        dto.getPassword(),
        dto.getF_name(),
        dto.getF_tel(),
        dto.getName(),
        dto.getBirth(),
        dto.getAddress(),
        dto.getHeight(),
        dto.getWeight(),
        dto.getBloodtype(),
        dto.getMedicine(),
        dto.getHospital_name(),
        dto.getDisease(),
        dto.getAllergic()
    );

    userRepository.save(user);
  }

  public boolean checkUserId(String userId){
    return userRepository.existsByUserId(userId);
  }

}
