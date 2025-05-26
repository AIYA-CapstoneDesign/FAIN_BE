package AIYA.com.FAIN.repository;

import AIYA.com.FAIN.entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FallAlertRepository extends JpaRepository<Reports, Integer> {

}
