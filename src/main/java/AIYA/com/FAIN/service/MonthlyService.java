package AIYA.com.FAIN.service;

import AIYA.com.FAIN.dto.CountResponseDto;
import AIYA.com.FAIN.dto.GraphResponseDto;
import AIYA.com.FAIN.dto.MonthlyRequestDto;
import AIYA.com.FAIN.dto.MonthlyResponseDto;
import AIYA.com.FAIN.entity.ActionType;
import AIYA.com.FAIN.entity.Reports;
import AIYA.com.FAIN.entity.Users;
import AIYA.com.FAIN.error.ErrorCode;
import AIYA.com.FAIN.error.exception.CustomException;
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

  public CountResponseDto getCount(String userId,Integer year,Integer month){
    Users users = userRepository.findByUserId(userId)
        .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));
    Integer fallCount = reportRepository.countByUserAndYearAndMonth(users,year,month);
    Integer hCount = reportRepository.countByUserAndYearAndMonthAndActionType(users,year,month,
        ActionType._119);
    Integer pCount = reportRepository.countByUserAndYearAndMonthAndActionType(users,year,month,
        ActionType.FAMILY);
    return CountResponseDto.builder()
        .fallCount(fallCount)
        .hCount(hCount)
        .pCount(pCount)
        .build();
  }

  public GraphResponseDto getGraph(String userId,Integer year,Integer month){
    Users users = userRepository.findByUserId(userId)
        .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));
    Integer dawnCount = reportRepository.countFallsBetweenHours(users,year,month,0,6);
    Integer morningCount = reportRepository.countFallsBetweenHours(users,year,month,6,12);
    Integer afternoonCount = reportRepository.countFallsBetweenHours(users,year,month,12,18);
    Integer nightCount = reportRepository.countFallsBetweenHours(users,year,month,18,24);
    return GraphResponseDto.builder()
        .dawn(dawnCount)
        .morning(morningCount)
        .afternoon(afternoonCount)
        .night(nightCount)
        .build();
  }

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
  public MonthlyResponseDto updateAndGetMonthlyReport(String userId,String gptMonthResponse){
    Users users = userRepository.findByUserId(userId)
        .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));


  }
}
