package AIYA.com.FAIN.security;

import AIYA.com.FAIN.entity.Users;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails  implements UserDetails {

  private final Users user;

  public CustomUserDetails(Users user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //권한 필요 없으면 빈 리스트반환(사용자 권한 구분 필요 없고 그냥 로그인만 하면됨)
    return Collections.emptyList();
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUserId();
  }

  // 계정이 만료되었는지 여부(false=만료)
  @Override
  public boolean isAccountNonExpired() { return true; }

  //계정이 잠겼는지(false=잠김)
  @Override
  public boolean isAccountNonLocked() { return true; }

  //비밀번호가 만료되었는지(false=만료)
  @Override
  public boolean isCredentialsNonExpired() { return true; }

  //계정이 활성화 상태인지(false=비활성화)
  @Override
  public boolean isEnabled() { return true; }

}
