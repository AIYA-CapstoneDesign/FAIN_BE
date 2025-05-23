package AIYA.com.FAIN.controller;

import AIYA.com.FAIN.dto.MypageResponseDto;
import AIYA.com.FAIN.jwt.JwtUtil;
import AIYA.com.FAIN.service.MypageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MypageController {

  private final MypageService mypageService;
  private final JwtUtil jwtUtil;

  public MypageController(JwtUtil jwtUtil, MypageService mypageService) {
    this.jwtUtil = jwtUtil;
    this.mypageService = mypageService;
  }

  @GetMapping("/api/v1/mypage")
  public ResponseEntity<MypageResponseDto> getMypage(@RequestHeader("Authorization") String header) {
    String token = header;
    if (header.startsWith("Bearer ")) {
      token = header.substring(7).trim(); // "Bearer " 자르고 trim()으로 공백없애기
    }

    String userId = jwtUtil.getUserIdFromToken(token);
    MypageResponseDto responseDto = mypageService.getMyPage(userId);

    return ResponseEntity.ok(responseDto);
  }

}
