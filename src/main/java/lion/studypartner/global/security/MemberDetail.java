package lion.studypartner.global.security;

import java.util.Collection;
import java.util.List;
import lion.studypartner.domain.member.dto.MemberResponse;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class MemberDetail implements UserDetails {

  private final MemberResponse member;

  @Override
  public String getUsername() {
    return member.getEmail();
  }

  @Override
  public String getPassword() {
    return null;
  }

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("email" + member.getEmail()));
  }
}
