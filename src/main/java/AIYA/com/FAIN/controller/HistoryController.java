package AIYA.com.FAIN.controller;

import AIYA.com.FAIN.dto.ApiResponseDto;
import AIYA.com.FAIN.dto.HistoryResponseDto;
import AIYA.com.FAIN.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryController {

  private final HistoryService historyService;

  public HistoryController(HistoryService historyService) {
    this.historyService = historyService;
  }
  @Operation(
      summary = "유저 히스토리 간략 조회",
      description = "유저 ID를 통해 이력 리스트를 조회한다. (JWT 인증 필요)",
      security = { @SecurityRequirement(name = "BearerAuth") }
  )
  @GetMapping("api/v1/history")
  public ResponseEntity<ApiResponseDto<List<HistoryResponseDto>>> getHistory() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();

    List<HistoryResponseDto> historyList = historyService.getHistoryListByUserId(userId);

    return ResponseEntity.ok(ApiResponseDto.success(historyList));
  }

}
