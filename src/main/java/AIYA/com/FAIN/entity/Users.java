package AIYA.com.FAIN.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter // 각 모든 필드에 대해 Getter 생성
@Setter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자
public class Users {
  public Users(String userId, String password, String f_name, String f_tel, String name, LocalDate birth,
      String address, String height, String weight, String bloodtype, String medicine, String hospital_name, String disease, String allergic ){
    this.userId = userId;
    this.password = password;
    this.f_name = f_name;
    this.f_tel = f_tel;
    this.name = name;
    this.birth=birth;
    this.address = address;
    this.height = height;
    this.weight = weight;
    this.bloodtype = bloodtype;
    this.medicine = medicine;
    this.hospital_name = hospital_name;
    this.disease = disease;
    this.allergic = allergic;

  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id; // 회원번호

  @Column(name ="user_id", nullable = false, unique=true)
  private String userId; //보호자 아이디

  @Column(nullable=false)
  private String password; // 보호자 비밀번호

  @Column(nullable=false)
  private String f_name;

  @Column(nullable=false)
  private String f_tel;

  @Column(nullable=false)
  private String name;

  @Column(nullable=false)
  private LocalDate birth;

  @Column(nullable=false)
  private String address;

  @Column(nullable=false)
  private String bloodtype;

  private String disease;

  private String hospital_name;

  private String height;

  private String weight;

  private String allergic;

  private String medicine;


}
