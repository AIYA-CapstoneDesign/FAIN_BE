package AIYA.com.FAIN.service;

import AIYA.com.FAIN.dto.UserDetailResponseDto;
import AIYA.com.FAIN.entity.ActionType;
import AIYA.com.FAIN.entity.Reports;
import AIYA.com.FAIN.entity.Users;
import AIYA.com.FAIN.error.ErrorCode;
import AIYA.com.FAIN.error.exception.CustomException;
import AIYA.com.FAIN.repository.ReportRepository;
import AIYA.com.FAIN.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportService {
  private final ReportRepository reportRepository;
  private final UserRepository userRepository;

  public ReportService(ReportRepository reportRepository,UserRepository userRepository) {
    this.reportRepository = reportRepository;
    this.userRepository = userRepository;
  }

  public UserDetailResponseDto getUserDetails(String userId){
    Users users = userRepository.findByUserId(userId)
        .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));
    return UserDetailResponseDto.builder()
        .name(users.getName())
        .birth(users.getBirth())
        .bloodtype(users.getBloodtype())
        .height(users.getHeight())
        .weight(users.getWeight())
        .disease(users.getDisease())
        .allergic(users.getAllergic())
        .medicine(users.getMedicine())
        .build();

  }

  @Transactional
  public void updateAction(Long reportId, ActionType actionType){
    Reports reports = reportRepository.findById(reportId)
        .orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_REPORTS));
    reports.updateAction(actionType);
  }

}
