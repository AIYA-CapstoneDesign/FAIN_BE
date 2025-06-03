package AIYA.com.FAIN.repository;

import AIYA.com.FAIN.entity.FcmToken;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// DB와 연결해 데이터를 읽고 쓰는 역할
public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {
  Optional<FcmToken> findByToken(String token); // 토큰 중복 방지를 위해 조회메서드
  List<FcmToken> findAllByUserId(String userId); // 유저의 모든 토큰 찾기
  Optional<FcmToken> findByUserIdAndToken(String userId, String token); // 유저+토큰의 중복 확인

}
