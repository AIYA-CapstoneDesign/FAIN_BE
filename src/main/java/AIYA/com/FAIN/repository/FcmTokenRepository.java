package AIYA.com.FAIN.repository;

import AIYA.com.FAIN.entity.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// DB와 연결해 데이터를 읽고 쓰는 역할
public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {
  Optional<FcmToken> findByToken(String token); // 토큰 중복 방지를 위해 조회메서드
  Optional<FcmToken> findByUserId(String userId);

}
