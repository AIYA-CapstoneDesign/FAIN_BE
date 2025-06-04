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
  public Users(String userId, String password, String fName, String fTel, String name, LocalDate birth,
      String address, String height, String weight, String bloodtype, String medicine, String hospitalName, String hospitalTel, String disease, String allergic ){
    this.userId = userId;
    this.password = password;
    this.fName = fName;
    this.fTel = fTel;
    this.name = name;
    this.birth=birth;
    this.address = address;
    this.height = height;
    this.weight = weight;
    this.bloodtype = bloodtype;
    this.medicine = medicine;
    this.hospitalName = hospitalName;
    this.hospitalTel = hospitalTel;
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

  @Column(name = "f_name",nullable=false)
  private String fName;

  @Column(name="f_tel",nullable=false)
  private String fTel;

  @Column(nullable=false)
  private String name;

  @Column(nullable=false)
  private LocalDate birth;

  @Column(nullable=false)
  private String address;

  private String height;

  private String weight;

  @Column(nullable=false)
  private String bloodtype;

  private String medicine;

  @Column(name = "hospital_name")
  private String hospitalName;

  private String hospitalTel;

  private String disease;

  private String allergic;


}
