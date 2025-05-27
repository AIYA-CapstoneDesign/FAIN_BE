package AIYA.com.FAIN.controller;

import AIYA.com.FAIN.dto.UpdateActionDto;
import AIYA.com.FAIN.dto.UserDetailResponseDto;
import AIYA.com.FAIN.jwt.JwtUtil;
import AIYA.com.FAIN.service.ReportService;
import com.google.firebase.database.core.Repo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ReportController {
  private final JwtUtil jwtUtil;
  private final ReportService reportService;

  public ReportController(JwtUtil jwtUtil, ReportService reportService) {
    this.jwtUtil = jwtUtil;
    this.reportService = reportService;
  }

  @Operation(
      summary = "환자 기본 정보 조회",
      description = "환자의 기본 정보를 조회합니다.",
      security = @SecurityRequirement(name = "BearerAuth")
  )
  @GetMapping("/details")
  public ResponseEntity<UserDetailResponseDto> getUserDetails() throws Exception{
//    String token = header;
//    if (header.startsWith("Bearer ")) {
//      token = header.substring(7).trim();
//    }
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();
    UserDetailResponseDto userDetailResponseDto = reportService.getUserDetails(userId);

    return ResponseEntity.ok(userDetailResponseDto);
  }

  @Operation(
      summary = "조치 방법 저장",
      description = "조치방법(119,보호자조치)을 저장합니다.",
      security = @SecurityRequirement(name="BearerAuth")
  )
  @PatchMapping("/actions/{reportId}")
  public ResponseEntity<?> updateAction(@PathVariable Long reportId,
      @RequestBody UpdateActionDto dto){
    reportService.updateAction(reportId,dto.getActionType());
    return ResponseEntity.ok("조치가 저장되었습니다.");

  }


}
