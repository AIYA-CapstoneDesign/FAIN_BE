package AIYA.com.FAIN.repository;

import AIYA.com.FAIN.entity.Reports;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Reports,Long> {
  List<Reports> findAllByUserId(Integer id);
}
