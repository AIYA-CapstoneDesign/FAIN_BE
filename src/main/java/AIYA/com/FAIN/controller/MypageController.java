package AIYA.com.FAIN.controller;

import AIYA.com.FAIN.dto.ApiResponseDto;
import AIYA.com.FAIN.dto.MypageResponseDto;
import AIYA.com.FAIN.dto.MypageUpdateRequestDto;
import AIYA.com.FAIN.jwt.JwtUtil;
import AIYA.com.FAIN.service.MypageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  // 마이페이지 조회 api
  @Operation(summary = "마이페이지 조회",description = "JWT토큰을 헤더에 담아 해당 유저의 마이페이지를 조회한다.")
  @GetMapping("/api/v1/mypage")
  public ResponseEntity<ApiResponseDto<MypageResponseDto>> getMypage(@Parameter(
      name = "Authorization",
      description = "Bearer {JWT 토큰}",
      required = true,
      example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
  @RequestHeader("Authorization") String header) {

    String token = header;
    if (header.startsWith("Bearer ")) {
      token = header.substring(7).trim(); // "Bearer " 자르고 trim()으로 공백없애기
    }

    String userId = jwtUtil.getUserIdFromToken(token);
    MypageResponseDto responseDto = mypageService.getMyPage(userId);

    return ResponseEntity.ok(ApiResponseDto.success(responseDto));
  }

  // 마이페이지 정보수정 api
  @Operation(summary = "마이페이지 정보 수정",description = "JWT 토큰을 헤더에 담아 해당 유저의 정보를 수정한다.")
  @PatchMapping("/api/v1/updateProfiles")
  public ResponseEntity<ApiResponseDto<Void>> updateProfiles(@Parameter(
      name = "Authorization",
      description = "Bearer {JWT 토큰}",
      required = true,
      example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
  @RequestHeader("Authorization") String header,
      @RequestBody MypageUpdateRequestDto dto){
    String token = header;
    if (header.startsWith("Bearer ")) {
      token = header.substring(7).trim(); // "Bearer " 자르고 trim()으로 공백없애기
    }
    String userId = jwtUtil.getUserIdFromToken(token);

    mypageService.UpdateMypage(userId,dto);
    return ResponseEntity.ok(ApiResponseDto.success(null));
  }

}
