package AIYA.com.FAIN.controller;

import AIYA.com.FAIN.dto.FallAlertRequestDto;
import AIYA.com.FAIN.entity.Reports;
import AIYA.com.FAIN.entity.Users;
import AIYA.com.FAIN.repository.FallAlertRepository;
import AIYA.com.FAIN.repository.UserRepository;
import AIYA.com.FAIN.service.FcmService;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertController {

  private final FcmService fcmService;
  private final FallAlertRepository fallAlertRepository;
  private final UserRepository userRepository;

  public AlertController(FcmService fcmService, FallAlertRepository fallAlertRepository,UserRepository userRepository) {
    this.fcmService = fcmService;
    this.fallAlertRepository = fallAlertRepository;
    this.userRepository = userRepository;
  }

  @PostMapping("/api/v1/notification/creates")
  public ResponseEntity<String> notifyFall(@RequestBody FallAlertRequestDto dto){
    // 유저아이디로 찾은 유저 객체 저장
    Users user = userRepository.findByUserId(dto.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));

    // 낙상 상황 DB 저장
    Reports reports = new Reports();
    reports.setUser(user); // 유저의 기본키(id)가 저장됨
    reports.setSituationImg(dto.getSituationImg());
    reports.setSituationTime(LocalDateTime.now());

    fallAlertRepository.save(reports);

    //알림 전송
    fcmService.sendMessage(dto.getUserId());

    return ResponseEntity.ok("success");
  }




}
