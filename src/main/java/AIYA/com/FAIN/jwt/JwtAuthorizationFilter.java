package AIYA.com.FAIN.jwt;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;

  public JwtAuthorizationFilter(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    // 1. Authorization 헤더에서 토큰 추출
    String header = request.getHeader("Authorization");
    String token;
    String userId = null;

    if (header != null && header.startsWith("Bearer ")) {
      // Bearer  잘라낸 순수 토큰
      token = header.substring(7);

      try {
        userId = jwtUtil.getUserIdFromToken(token);
      } catch (Exception e) {
        filterChain.doFilter(request, response);
        return;
      }
    }

    // 3. 인증 처리 (토큰이 있고, 아직 인증되지 않았다면)
    if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      User userDetails = new User(userId, "", Collections.emptyList());

      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    // 4. 다음 필터로 진행
    filterChain.doFilter(request, response);
  }
}
