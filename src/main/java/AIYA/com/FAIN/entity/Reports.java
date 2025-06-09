package AIYA.com.FAIN.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="reports")
public class Reports {

  @Id
  @Column(name="report_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reportId;

  @ManyToOne
  @JoinColumn(name = "id",referencedColumnName = "id",nullable = false)
  private Users user;

  @Column(nullable = true)
  private String report;

  @Column(name="situation_img",nullable = true)
  private String situationImg;

  @Enumerated(EnumType.STRING)
  @Column(name="action_type",nullable = true)
  private ActionType actionType;

  @CreationTimestamp //처음 insert될 때, 자동으로 시간 입력, 이후 변경되지 않음
  @Column(name="situation_time",updatable = false,nullable = false)
  private ZonedDateTime situationTime;

  public void updateAction(ActionType actionType){
    if(this.actionType != null){
      throw new IllegalStateException("이미 조치된 리포트입니다.");
    }
    this.actionType = actionType;

  }

  public void updateReports(String gptResponse){
    if(this.report != null){
      throw new IllegalArgumentException("이미 생성된 리포트입니다.");
    }
    this.report = gptResponse;
  }

}
