package AIYA.com.FAIN.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//토큰 확인 및 생성 메서드
@Component
public class JwtUtil {
  private final SecretKey secretKey;

  // JWT 서명에 쓸 비밀키 읽어와서 비밀키 생성
  public JwtUtil(@Value("${spring.jwt.secret}") String secret) {
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }
  // 토큰 생성
  public String createToken(String userId){
    return Jwts.builder().setSubject(userId).setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30))
        .signWith(SignatureAlgorithm.HS256,secretKey)
        .compact();
  }
  // 토큰에서 유저ID 추출
  public String getUserIdFromToken(String token){
    return Jwts.parser()
        .verifyWith(secretKey) // 비밀키 대조
        .build().parseSignedClaims(token) // 전달된 JWT 토큰 문자열해석해서 header,payload,signature정보 추출
        .getPayload()
        .get("sub",String.class);
  }

}
