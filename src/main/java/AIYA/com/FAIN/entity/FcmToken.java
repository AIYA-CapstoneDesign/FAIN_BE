package AIYA.com.FAIN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fcm_tokens")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FcmToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId", nullable = false)
  private Users user;

  @Column(length = 512, nullable = false, unique = true)
  private String token;

  public FcmToken(Users user, String token) {
    this.user = user;
    this.token = token;
  }
}
