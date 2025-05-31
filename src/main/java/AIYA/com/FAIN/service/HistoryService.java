package AIYA.com.FAIN.service;

import AIYA.com.FAIN.dto.HistoryResponseDto;
import AIYA.com.FAIN.entity.Reports;
import AIYA.com.FAIN.entity.Users;
import AIYA.com.FAIN.repository.ReportRepository;
import AIYA.com.FAIN.repository.UserRepository;
import java.util.List;
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

}
