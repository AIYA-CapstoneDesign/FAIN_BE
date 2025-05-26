package AIYA.com.FAIN.controller;

import AIYA.com.FAIN.dto.FallAlertRequestDto;
import AIYA.com.FAIN.entity.Reports;
import AIYA.com.FAIN.repository.FallAlertRepository;
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

  public AlertController(FcmService fcmService, FallAlertRepository fallAlertRepository) {
    this.fcmService = fcmService;
    this.fallAlertRepository = fallAlertRepository;
  }

  @PostMapping("/api/v1/notification/creates")
  public ResponseEntity<String> notifyFall(@RequestBody FallAlertRequestDto dto){
    // 낙상 상황 DB 저장
    Reports reports = new Reports();
    reports.setSituationImg(dto.getSituationImg());
    reports.setSituationTime(LocalDateTime.now());

    fallAlertRepository.save(reports);

    //알림 전송
    fcmService.sendMessage(dto.getUserId());

    return ResponseEntity.ok("success");
  }




}
