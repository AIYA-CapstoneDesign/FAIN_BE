package AIYA.com.FAIN.controller;

import AIYA.com.FAIN.dto.ApiResponseDto;
import AIYA.com.FAIN.dto.HistoryDetailResponseDto;
import AIYA.com.FAIN.dto.HistoryResponseDto;
import AIYA.com.FAIN.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HistoryController {

  private final HistoryService historyService;


  public HistoryController(HistoryService historyService) {
    this.historyService = historyService;
  }
  // 히스토리 간략 조희
  @Operation(
      summary = "유저 히스토리 간략 조회",

      description = "유저 ID를 통해 이력 리스트를 조회한다. (JWT 인증 필요)",

      security = { @SecurityRequirement(name = "BearerAuth") }
  )

  @GetMapping("/history")

  public ResponseEntity<ApiResponseDto<List<HistoryResponseDto>>> getHistory() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();

    List<HistoryResponseDto> historyList = historyService.getHistoryListByUserId(userId);

    return ResponseEntity.ok(ApiResponseDto.success(historyList));
  }

  // 히스토리 상세조회
  @Operation(
      summary = "히스토리 상세 조회",
      description = "특정 리포트ID의 낙상 상세 이력",
      security = @SecurityRequirement(name = "BearerAuth")
  )
  @GetMapping("/history/{reportId}")
  public ResponseEntity<ApiResponseDto<HistoryDetailResponseDto>> getHistoryDetail(@PathVariable("reportId") Long reportId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();

    HistoryDetailResponseDto detail = historyService.getHistoryDetailByReportIdAndUserId(reportId, userId);

    return ResponseEntity.ok(ApiResponseDto.success(detail));
  }

}
