package AIYA.com.FAIN.service;

import AIYA.com.FAIN.dto.UserSignupDto;
import AIYA.com.FAIN.entity.Users;
import AIYA.com.FAIN.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public void signup(UserSignupDto dto){
    if (userRepository.existsByUserId(dto.getUserId())) {
      throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
    }
    // 비밀번호 암호화
    String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
    Users user = new Users(
        dto.getUserId(),
        encodedPassword,
        dto.getFName(),
        dto.getFTel(),
        dto.getName(),
        dto.getBirth(),
        dto.getAddress(),
        dto.getHeight(),
        dto.getWeight(),
        dto.getBloodtype(),
        dto.getMedicine(),
        dto.getHospitalName(),
        dto.getDisease(),
        dto.getAllergic()
    );
    userRepository.save(user);
  }

  public boolean checkUserId(String userId){
    return userRepository.existsByUserId(userId);
  }

}
