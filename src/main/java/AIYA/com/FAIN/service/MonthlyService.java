package AIYA.com.FAIN.service;

import AIYA.com.FAIN.dto.CountResponseDto;
import AIYA.com.FAIN.dto.GraphResponseDto;
import AIYA.com.FAIN.dto.MonthlyRequestDto;
import AIYA.com.FAIN.dto.MonthlyResponseDto;
import AIYA.com.FAIN.entity.ActionType;
import AIYA.com.FAIN.entity.MonthlyReports;
import AIYA.com.FAIN.entity.Reports;
import AIYA.com.FAIN.entity.Users;
import AIYA.com.FAIN.error.ErrorCode;
import AIYA.com.FAIN.error.exception.CustomException;
import AIYA.com.FAIN.repository.MonthlyReportRepository;
import AIYA.com.FAIN.repository.ReportRepository;
import AIYA.com.FAIN.repository.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class MonthlyService {
  private final UserRepository userRepository;
  private final ReportRepository reportRepository;
  private final MonthlyReportRepository monthlyReportRepository;

  @Transactional
  public CountResponseDto getCount(String userId,Integer year,Integer month){
    Users users = userRepository.findByUserId(userId)
        .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));
    Integer fallCount = reportRepository.countByUserAndYearAndMonth(users,year,month);
    Integer hCount = reportRepository.countByUserAndYearAndMonthAndActionType(users,year,month,
        ActionType._119);
    Integer pCount = reportRepository.countByUserAndYearAndMonthAndActionType(users,year,month,
        ActionType.FAMILY);
    MonthlyReports monthlyReports = monthlyReportRepository.findByUserAndYearAndMonth(users,year,month)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MONTHLY));
    monthlyReports.updateCount(fallCount,hCount,pCount);
    monthlyReportRepository.save(monthlyReports);
    return CountResponseDto.builder()
        .fallCount(fallCount)
        .hCount(hCount)
        .pCount(pCount)
        .build();
  }
  @Transactional
  public GraphResponseDto getGraph(String userId,Integer year,Integer month){
    Users users = userRepository.findByUserId(userId)
        .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));
    Integer dawnCount = reportRepository.countFallsBetweenHours(users.getId(),year,month,0,6);
    Integer morningCount = reportRepository.countFallsBetweenHours(users.getId(),year,month,6,12);
    Integer afternoonCount = reportRepository.countFallsBetweenHours(users.getId(),year,month,12,18);
    Integer nightCount = reportRepository.countFallsBetweenHours(users.getId(),year,month,18,24);
    MonthlyReports monthlyReports = monthlyReportRepository.findByUserAndYearAndMonth(users,year,month)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MONTHLY));
    monthlyReports.updateGraph(dawnCount,morningCount,afternoonCount,nightCount);
    monthlyReportRepository.save(monthlyReports);

    return GraphResponseDto.builder()
        .dawn(dawnCount)
        .morning(morningCount)
        .afternoon(afternoonCount)
        .night(nightCount)
        .build();
  }
  @Transactional
  public MonthlyRequestDto getMonthlyPrompt(String userId,Integer year,Integer month){
    Users users = userRepository.findByUserId(userId)
        .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));
    Integer fallCount = reportRepository.countByUserAndYearAndMonth(users,year,month);
    Integer hCount = reportRepository.countByUserAndYearAndMonthAndActionType(users,year,month,
        ActionType._119);
    Integer pCount = reportRepository.countByUserAndYearAndMonthAndActionType(users,year,month,
        ActionType.FAMILY);
    List<String> monthlyReportList = reportRepository.findAllReportContentsByUserAndYearAndMonth(users,year,month);


    return MonthlyRequestDto.builder()
        .name(users.getName())
        .birth(users.getBirth())
        .height(users.getHeight())
        .weight(users.getWeight())
        .medicine(users.getMedicine())
        .disease(users.getDisease())
        .allergic(users.getAllergic())
        .fallCount(fallCount)
        .hCount(hCount)
        .pCount(pCount)
        .monthlyReportHistories(monthlyReportList)
        .build();
  }

  @Transactional
  public MonthlyResponseDto updateAndGetMonthlyReport(String userId,String gptMonthResponse,Integer year,Integer month){
    Users users = userRepository.findByUserId(userId)
        .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));

    MonthlyReports monthlyReports = monthlyReportRepository.findByUserAndYearAndMonth(users,year,month)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MONTHLY));
    monthlyReports.updateAiComment(gptMonthResponse);
    monthlyReportRepository.save(monthlyReports);


    return MonthlyResponseDto.builder()
        .aiComment(gptMonthResponse)
        .build();
  }
}
