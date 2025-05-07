package AIYA.com.FAIN.controller;

import AIYA.com.FAIN.dto.FcmTokenRequest;
import AIYA.com.FAIN.entity.FcmToken;
import AIYA.com.FAIN.repository.FcmTokenRepository;
import AIYA.com.FAIN.service.FcmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fcm")
@RequiredArgsConstructor
public class FcmController {

  private final FcmTokenRepository fcmTokenRepository;
  private final FcmService fcmService;

  @PostMapping("/registers")
  public ResponseEntity<String> registerToken(@RequestBody FcmTokenRequest request) {
    String token = request.getToken();
    System.out.println("등록된 토큰: " + request.getToken());

    // 중복 등록 방지 : 이미 존재하는 토큰이면 저장하지 않음
    fcmTokenRepository.findByToken(token).orElseGet(() -> {
      FcmToken fcmToken = new FcmToken();
      fcmToken.setToken(token);
      fcmToken.setUser_Id(1L);
      return fcmTokenRepository.save(fcmToken);
    });

    return ResponseEntity.ok("토큰 등록 완료");
  }

  @GetMapping("/alert")
  public ResponseEntity<String> sendFallAlert(){
    List<FcmToken> tokenList = fcmTokenRepository.findAll();
    if(tokenList.isEmpty()){
      return ResponseEntity.badRequest().body("저장된 토큰이 없습니다");
    }
    for(FcmToken token : tokenList){
      fcmService.sendMessage(token.getToken());
    }
    return ResponseEntity.ok("낙상 알림 전송 완료");
  }

}
