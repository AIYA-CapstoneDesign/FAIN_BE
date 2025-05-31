package AIYA.com.FAIN.controller;

import AIYA.com.FAIN.dto.ApiResponseDto;
import AIYA.com.FAIN.dto.HistoryResponseDto;
import AIYA.com.FAIN.service.HistoryService;
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

  @GetMapping("api/v1/history")
  public ResponseEntity<ApiResponseDto<List<HistoryResponseDto>>> getHistory() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();

    List<HistoryResponseDto> historyList = historyService.getHistoryListByUserId(userId);

    return ResponseEntity.ok(ApiResponseDto.success(historyList));
  }

}
