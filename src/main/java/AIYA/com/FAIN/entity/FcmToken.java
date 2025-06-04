package AIYA.com.FAIN.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 자바 객체를 DB에 연동해주는
@Table(name="fcm_tokens", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId","token"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FcmToken {

  public FcmToken(String userId, String token) {
    this.userId = userId;
    this.token = token;
  }

  @Id // 기본키
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // 자동 증가되는 DB의 기본티

  private String userId; // 사용자의 ID. 나중에 연결

  @Column(length=512, nullable = false, unique =true)
  private String token; // 토큰

}
