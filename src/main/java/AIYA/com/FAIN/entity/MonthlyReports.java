package AIYA.com.FAIN.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

  @Column(nullable = true,name="ai_comment")
  private String aiComment;

  @Column(nullable = false)
  private Integer dawn;

  @Column(nullable = false)
  private Integer morning;

  @Column(nullable = false)
  private Integer afternoon;

  @Column(nullable = false)
  private Integer night;

  public void updateAiComment(String gptResponse){
    if(this.aiComment != null){
      throw new IllegalStateException("이미 생성된 월간리포트입니다.");
    }
    this.aiComment = aiComment;
  }

}
