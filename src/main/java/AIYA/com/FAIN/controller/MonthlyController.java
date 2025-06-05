package AIYA.com.FAIN.controller;

import AIYA.com.FAIN.client.PythonApiClient;
import AIYA.com.FAIN.dto.ApiResponseDto;
import AIYA.com.FAIN.dto.CountResponseDto;
import AIYA.com.FAIN.dto.GraphResponseDto;
import AIYA.com.FAIN.dto.MonthlyRequestDto;
import AIYA.com.FAIN.dto.MonthlyResponseDto;
import AIYA.com.FAIN.jwt.JwtUtil;
import AIYA.com.FAIN.service.MonthlyService;
import io.grpc.Grpc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/month")
public class MonthlyController {
  private final JwtUtil jwtUtil;
  private MonthlyService monthlyService;
  private PythonApiClient pythonApiClient;

  @Operation(
      summary = "낙상 관련 통계",
      description = "낙상횟수, 병원이송횟수, 자체조치 횟수를 통계냅니다.",
      security = @SecurityRequirement(name = "BearerAuth")
  )
  @GetMapping("/counts")
  public ResponseEntity<ApiResponseDto<CountResponseDto>> getCounts(@RequestParam("year") Integer year,@RequestParam("month") Integer month) throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();

    CountResponseDto countResponseDto = monthlyService.getCount(userId,year,month);

    return ResponseEntity.ok(ApiResponseDto.success(countResponseDto));
  }
  @Operation(
      summary = "그래프 데이터 조회",
      description = "시간대별로 낙상 횟수에 관한 그래프에 필요한 데이터를 제공합니다",
      security = @SecurityRequirement(name = "BearerAuth")
  )
  @GetMapping("/graphs")
  public ResponseEntity<ApiResponseDto<GraphResponseDto>> getGraphs(@RequestParam("year") Integer year,@RequestParam("month") Integer month) throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();

    GraphResponseDto graphResponseDto = monthlyService.getGraph(userId,year,month);

    return ResponseEntity.ok(ApiResponseDto.success(graphResponseDto));

  }

  @Operation(
      summary = "AI월간 종합리포트",
      description = "한달동안의 리포트를 종합해 AI 종합 건강리포트를 제공합니다.",
      security = @SecurityRequirement(name = "BearerAuth")
  )
  @GetMapping("/reports")
  public ResponseEntity<ApiResponseDto<MonthlyResponseDto>> getMonthlyReports(@RequestParam("year") Integer year,@RequestParam("month") Integer month) throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();

    //user와 해당년월의 report 가지고 GPT에 보내기
    MonthlyRequestDto requestDto = monthlyService.getMonthlyPrompt(userId,year,month);
    //응답 받아오기
    String gptMonthResponse = pythonApiClient.monthlyReport(requestDto);
    //DB에 응답 저장하고 MonthlyResponseDto에 Monthlyreport 담기
    MonthlyResponseDto monthlyResponseDto = monthlyService.updateAndGetMonthlyReport(userId,gptMonthResponse,year,month);
    //response 프론트로 return 하기
    return ResponseEntity.ok(ApiResponseDto.success(monthlyResponseDto));

  }

}
