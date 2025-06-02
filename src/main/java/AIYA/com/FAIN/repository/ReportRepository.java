package AIYA.com.FAIN.repository;

import AIYA.com.FAIN.entity.Reports;
import AIYA.com.FAIN.entity.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Reports,Long> {
  List<Reports> findAllByUserId(Integer id);
  List<Reports> findAllByUser(Users user);
  Optional<Reports> findByReportIdAndUserId(Long reportId, Integer id);


}
