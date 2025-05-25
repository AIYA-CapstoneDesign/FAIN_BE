package AIYA.com.FAIN.repository;

import AIYA.com.FAIN.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// Users entity와 DB를 연결해주는 다리 역할.
// SQL을 직접 작성하지 않아도 Spring data JPA가 이 인터페이스를 기반으로 자동으로 쿼리 생성
public interface UserRepository extends JpaRepository<Users, Integer> {
  boolean existsByUserId(String userId);
  Optional<Users> findByUserId(String userId);
}
