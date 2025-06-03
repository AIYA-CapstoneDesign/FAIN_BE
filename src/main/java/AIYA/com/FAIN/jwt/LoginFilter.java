package AIYA.com.FAIN.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
    setFilterProcessesUrl("/api/v1/login");
  }

//  @Override
//  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//    System.out.println("[DEBUG] LoginFilter - attemptAuthentication 호출됨");
//
//    try {
//      ObjectMapper objectMapper = new ObjectMapper();
//      Map<String, String> jsonRequest = objectMapper.readValue(request.getInputStream(), Map.class);
//
//      String userId = jsonRequest.get("userId");
//      String password = jsonRequest.get("password");
//
//      System.out.println("[DEBUG] userId=" + userId + ", password=" + (password != null ? "*****" : null));
//
//      if(userId == null) userId = "";
//      if(password == null) password = "";
//
//      UsernamePasswordAuthenticationToken authToken =
//          new UsernamePasswordAuthenticationToken(userId, password);
//
//      // 실제 인증 처리
//      return authenticationManager.authenticate(authToken);
//
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    System.out.println("[DEBUG] LoginFilter - attemptAuthentication 호출됨");

    // [1] OPTIONS 요청은 무시 (CORS Preflight)
    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
      System.out.println("[DEBUG] OPTIONS 요청 무시됨");
      response.setStatus(HttpServletResponse.SC_OK);
      return null;
    }

    try {
      // [2] 요청 본문이 비었는지 먼저 확인
      if (request.getInputStream() == null || request.getContentLength() == 0) {
        System.out.println("[DEBUG] 요청 본문이 비어 있음");
        throw new RuntimeException("요청 본문이 비어 있음");
      }

      // [3] JSON 파싱
      ObjectMapper objectMapper = new ObjectMapper();
      Map<String, String> jsonRequest = objectMapper.readValue(request.getInputStream(), Map.class);

      String userId = jsonRequest.get("userId");
      String password = jsonRequest.get("password");

      System.out.println("[DEBUG] userId=" + userId + ", password=" + (password != null ? "*****" : null));

      if (userId == null) userId = "";
      if (password == null) password = "";

      UsernamePasswordAuthenticationToken authToken =
          new UsernamePasswordAuthenticationToken(userId, password);

      return authenticationManager.authenticate(authToken);

    } catch (IOException e) {
      throw new RuntimeException("[LoginFilter] JSON 파싱 실패", e);
    }
  }


  //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)throws IOException {
    System.out.println("[DEBUG] LoginFilter - 로그인 성공 userId=" + authentication.getName());
    String userId = authentication.getName();
    String token = jwtUtil.createToken(userId);

    response.setContentType("application/json;charset=utf-8");
    response.getWriter().write("{\"token\":\""+token+"\"}");
    response.getWriter().flush();
  }
  //로그인 실패시 실행하는 메소드
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)throws IOException {
    System.out.println("[DEBUG] LoginFilter - 로그인 실패: " + failed.getMessage());
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.getWriter().write("{\"error\": \"로그인 실패\"}");
    response.getWriter().flush();
  }

}
