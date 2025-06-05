package AIYA.com.FAIN.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="monthly_reports")
public class MonthlyReports {
  @Id
  @Column(name="mr_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer mrId;

  @ManyToOne
  @JoinColumn(name = "id",referencedColumnName = "id",nullable = false)
  private Users user;

  @Column(nullable = false)
  private Integer year;

  @Column(nullable = false)
  private Integer month;

  @Column(nullable = true,name="gpt_report")
  private String gptReport;
  @Column(nullable = true,name="fall_count")
  private Integer fallCount;

  @Column(nullable = true,name="h_count")
  private Integer hCount;

  @Column(nullable = true,name="p_count")
  private Integer pCount;

  @Column(nullable = true,name="ai_comment" ,columnDefinition = "LONGTEXT")
  private String aiComment;

  @Column(nullable = true)
  private Integer dawn;

  @Column(nullable = true)
  private Integer morning;

  @Column(nullable = true)
  private Integer afternoon;

  @Column(nullable = true)
  private Integer night;

  public void updateAiComment(String gptResponse){
    if(this.aiComment != null){
      throw new IllegalStateException("이미 생성된 월간리포트입니다.");
    }
    this.aiComment = aiComment;
  }

  public void updateCount(Integer fallCount,Integer hCount,Integer pCount){
    this.fallCount = fallCount;
    this.hCount = hCount;
    this.pCount = pCount;
  }

  public void updateGraph(Integer dawn,Integer morning, Integer afternoon, Integer night){
    this.dawn = dawn;
    this.morning = morning;
    this.afternoon = afternoon;
    this.night = night;
  }

  public void setDawn(Integer dawn) {
    this.dawn = dawn;
  }

  public void setMorning(Integer morning) {
    this.morning = morning;
  }

  public void setAfternoon(Integer afternoon) {
    this.afternoon = afternoon;
  }

  public void setNight(Integer night) {
    this.night = night;
  }

  public MonthlyReports(Users user, Integer year, Integer month) {
    this.user = user;
    this.year = year;
    this.month = month;
  }
}
