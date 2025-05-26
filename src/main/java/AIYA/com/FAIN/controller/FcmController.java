package AIYA.com.FAIN.controller;

import AIYA.com.FAIN.dto.FcmTokenRequestDto;
import AIYA.com.FAIN.entity.FcmToken;
import AIYA.com.FAIN.jwt.JwtUtil;
import AIYA.com.FAIN.repository.FcmTokenRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fcm")
@RequiredArgsConstructor
public class FcmController {

  private final FcmTokenRepository fcmTokenRepository;
  private final JwtUtil jwtUtil;

  // 로그인 후 토큰 등록 api
  @Operation(summary = "FCM 토큰 등록", description = "로그인한 사용자의 발급된 토큰을 등록한다.")
  @PostMapping("/registers")
  public ResponseEntity<String> registerToken(@Parameter(
      name = "Authorization",
      description = "Bearer {token}",
      required = true,
      example = "Bearer eyJhbGciOiJIUzI1NiJ9..."
  )@RequestHeader("Authorization") String JwtToken, @RequestBody FcmTokenRequestDto dto) {
    String jwtToken = JwtToken.substring(7);
    String userId = jwtUtil.getUserIdFromToken(jwtToken);

    // 중복 등록 방지 : 이미 존재하는 토큰이면 저장하지 않음
    fcmTokenRepository.findByToken(dto.getToken()).orElseGet(() -> {
      FcmToken fcmToken = new FcmToken();
      fcmToken.setToken(dto.getToken());
      fcmToken.setUserId(userId);
      return fcmTokenRepository.save(fcmToken);
    });

    return ResponseEntity.ok("토큰 등록 완료");
  }


}
