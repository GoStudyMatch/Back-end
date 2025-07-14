package lion.studypartner.domain.member.service;

import lion.studypartner.domain.member.dto.MemberRequest.SignIn;
import lion.studypartner.domain.member.dto.MemberRequest.SignUp;
import lion.studypartner.domain.member.dto.MemberResponse;
import lion.studypartner.domain.member.entity.Member;
import lion.studypartner.domain.member.repository.MemberRepository;
import lion.studypartner.global.expeciton.CustomException;
import lion.studypartner.global.expeciton.ErrorCode;
import lion.studypartner.global.security.JwtProvider;
import lion.studypartner.global.security.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JwtProvider jwtProvider;

  public MemberResponse signUp(SignUp request) {
    if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
      throw new CustomException(ErrorCode.EMAIL_EXIST);
    }

    Member member = Member.from(request);
    member.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

    return MemberResponse.from(memberRepository.save(member));
  }

  public TokenDto signIn(SignIn request) {
    Member member = memberRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_FAIL));

    if (!bCryptPasswordEncoder.matches(request.getPassword(), member.getPassword())) {
      throw new CustomException(ErrorCode.LOGIN_FAIL);
    }

    String accessToken = jwtProvider.createAccessToken(member.getId(), member.getEmail());
    String refreshToken = jwtProvider.createRefreshToken(member.getId(), member.getEmail());

    member.setRefreshToken(refreshToken);
    memberRepository.save(member);

    return TokenDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }
}
