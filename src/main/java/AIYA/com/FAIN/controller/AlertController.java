package AIYA.com.FAIN.controller;

import AIYA.com.FAIN.dto.ApiResponseDto;
import AIYA.com.FAIN.dto.FallAlertRequestDto;
import AIYA.com.FAIN.entity.Reports;
import AIYA.com.FAIN.entity.Users;
import AIYA.com.FAIN.repository.FallAlertRepository;
import AIYA.com.FAIN.repository.UserRepository;
import AIYA.com.FAIN.service.FcmService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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
  public ResponseEntity<ApiResponseDto<Map<String,Long>>> notifyFall(@io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "낙상 감지 시 전달받는 사진 및 유저 아이디 정보",
      required = true
  )@RequestBody FallAlertRequestDto dto){
    // 유저아이디로 찾은 유저 객체 저장
    Users user = userRepository.findByUserId(dto.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));

    // 낙상 상황 DB 저장
    Reports reports = new Reports();
    reports.setUser(user); // 유저의 기본키(id)가 저장됨
    reports.setSituationImg(dto.getSituationImg());
    reports.setSituationTime(LocalDateTime.now());

    fallAlertRepository.save(reports);

    // 알림 전송
    fcmService.sendMessage(dto.getUserId(), reports.getReportId());

    Map<String,Long> data = new HashMap<>();
    data.put("reportId", reports.getReportId());

    // 응답 생성
    ApiResponseDto<Map<String,Long>> response = ApiResponseDto.successDataAndMessage(data,"낙상이 감지되었습니다.");

    return ResponseEntity.ok(response);
  }




}
