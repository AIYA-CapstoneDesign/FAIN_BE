package AIYA.com.FAIN.config;

import AIYA.com.FAIN.jwt.JwtUtil;
import AIYA.com.FAIN.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  // 비밀번호를 암호화하여 return
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
  private final AuthenticationConfiguration authenticationConfiguration;

  public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {

    this.authenticationConfiguration = authenticationConfiguration;
  }

  //AuthenticationManager Bean 등록
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

    return configuration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration, JwtUtil jwtUtil) throws Exception {
    // crsf disable
    http.csrf((auth) -> auth.disable());
    // From 로그인 방식 disable (spring security 자체에서 제공하는 login api 끄기)
    http.formLogin((auth) -> auth.disable());
    // http basic 인증 방식 disable (스프링 내부에서 제공하는 base64로 ID/PW 담기 끄기 -> 우리는 JWT로)
    http.httpBasic((auth) -> auth.disable());
    // 허용해주는 API
    http.authorizeHttpRequests((auth) -> auth
        .requestMatchers(
            "/api/v1/login",
            "/",
            "/join",
            "/api/v1/signup",
            "/api/v1/signup/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
        ).permitAll()
        .anyRequest().authenticated());

    http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil), UsernamePasswordAuthenticationFilter.class);

    // 세션 비활성화 설정.사용자를 인증하면 서버 세션에 인증 정보를 저장. 하지만 JWT 방식은 매 요청마다 토큰을 보내서 저장같은건 필요 없음
    http.sessionManagement(
        (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }
}



