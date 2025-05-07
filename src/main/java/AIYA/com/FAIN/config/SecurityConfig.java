package AIYA.com.FAIN.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // ✅ CSRF 비활성화 (Postman에서 POST 요청 테스트 위해)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/fcm/**").permitAll() // ✅ fcm 관련 경로 허용
            .anyRequest().permitAll() // 🔥 모든 요청 허용 (개발 중에만)
        );

    return http.build();
  }
}
