package AIYA.com.FAIN.repository;

import AIYA.com.FAIN.entity.MonthlyReports;
import AIYA.com.FAIN.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyReportRepository extends JpaRepository<MonthlyReports,Integer> {

  Boolean existsByUserAndYearAndMonth(Users users,Integer year,Integer month);

  Optional<MonthlyReports> findByUserAndYearAndMonth(Users users,Integer year,Integer month);
}
