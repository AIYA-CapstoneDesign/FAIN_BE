package AIYA.com.FAIN.repository;

import AIYA.com.FAIN.entity.ActionType;
import AIYA.com.FAIN.entity.Reports;
import AIYA.com.FAIN.entity.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<Reports,Long> {
  List<Reports> findAllByUserId(Integer id);
  List<Reports> findAllByUser(Users user);
  Optional<Reports> findByReportIdAndUserId(Long reportId, Integer id);

  Optional<Reports> findByReportIdAndUser(Long reportId, Users users);

  @Query("SELECT COUNT(r) FROM Reports r WHERE r.user = :user AND YEAR(r.situationTime) = :year AND MONTH(r.situationTime) = :month")
  Integer countByUserAndYearAndMonth(@Param("user") Users user,
      @Param("year") int year,
      @Param("month") int month);

  @Query("SELECT COUNT(r) FROM Reports r " +
      "WHERE r.user = :user " +
      "AND YEAR(r.situationTime) = :year " +
      "AND MONTH(r.situationTime) = :month " +
      "AND r.actionType = :actionType")
  Integer countByUserAndYearAndMonthAndActionType(@Param("user") Users user,
      @Param("year") int year,
      @Param("month") int month,
      @Param("actionType") ActionType actionType);

  @Query(value = """
  SELECT COUNT(*) FROM reports r
  WHERE r.id = :userId
    AND YEAR(r.situation_time) = :year
    AND MONTH(r.situation_time) = :month
    AND HOUR(r.situation_time) >= :startHour
    AND HOUR(r.situation_time) < :endHour
""", nativeQuery = true)
  Integer countFallsBetweenHours(@Param("userId") Integer userId,
      @Param("year") Integer year,
      @Param("month") Integer month,
      @Param("startHour") int startHour,
      @Param("endHour") int endHour);

  @Query("SELECT r.report FROM Reports r " +
      "WHERE r.user = :user " +
      "AND YEAR(r.situationTime) = :year " +
      "AND MONTH(r.situationTime) = :month " +
      "AND r.report IS NOT NULL")
  List<String> findAllReportContentsByUserAndYearAndMonth(@Param("user") Users user,
      @Param("year") int year,
      @Param("month") int month);
}
