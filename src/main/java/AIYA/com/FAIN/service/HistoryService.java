package AIYA.com.FAIN.service;

import AIYA.com.FAIN.dto.HistoryDetailResponseDto;
import AIYA.com.FAIN.dto.HistoryResponseDto;
import AIYA.com.FAIN.entity.Reports;
import AIYA.com.FAIN.entity.Users;
import AIYA.com.FAIN.repository.ReportRepository;
import AIYA.com.FAIN.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

  private final ReportRepository reportRepository;
  private final UserRepository userRepository;

  public HistoryService(ReportRepository reportRepository, UserRepository userRepository) {
    this.reportRepository = reportRepository;
    this.userRepository = userRepository;
  }

  public List<HistoryResponseDto> getHistoryListByUserId(String userId){
    Users user = userRepository.findByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다: " + userId));
    Integer id = user.getId();
    List<Reports> reports = reportRepository.findAllByUserId(id);

    return reports.stream().map(r -> new HistoryResponseDto(
        r.getReportId().toString(),
        r.getSituationTime().toString(),
        r.getActionType().toString()
    )).collect(Collectors.toList());
  }

  // 히스토리 상세보기
  public HistoryDetailResponseDto getHistoryDetailByReportIdAndUserId(Long reportId, String userId){
    //1. userId로 user엔티티 찾기
    Optional<Users> user = userRepository.findByUserId(userId);
    if(user.isEmpty()){
      throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
    }
    Users users = user.get();

    // 2. 해당 유저의 reportId와 일치하는 리포트 찾기
    Reports reports = reportRepository.findByReportIdAndUserId(reportId, user.get().getId())
        .orElseThrow(()->new IllegalArgumentException("리포트를 찾을 수 없습니다."));

    // 3. Dto에 담아서 반환
    return new HistoryDetailResponseDto(
        users.getName(),
        reports.getSituationTime().toString(),
        reports.getSituationImg(),
        reports.getReport(),
        reports.getActionType().value()
    );
  }

}
