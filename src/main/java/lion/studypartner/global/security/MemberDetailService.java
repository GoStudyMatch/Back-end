package lion.studypartner.global.security;

import lion.studypartner.domain.member.dto.MemberResponse;
import lion.studypartner.domain.member.entity.Member;
import lion.studypartner.domain.member.repository.MemberRepository;
import lion.studypartner.global.expeciton.CustomException;
import lion.studypartner.global.expeciton.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public MemberDetail loadUserByUsername(String email) {

    Member member = memberRepository.findByEmail(email)
        .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_EXIST));

    return new MemberDetail(MemberResponse.from(member));
  }
}
