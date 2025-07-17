package lion.studypartner.domain.member.service;

import lion.studypartner.domain.member.dto.MemberRequest;
import lion.studypartner.domain.member.dto.MemberResponse;
import lion.studypartner.domain.member.entity.Member;
import lion.studypartner.domain.member.repository.MemberRepository;
import lion.studypartner.global.expeciton.CustomException;
import lion.studypartner.global.expeciton.ErrorCode;
import lion.studypartner.global.security.JwtProvider;
import lion.studypartner.global.security.TokenDto;
import lion.studypartner.global.type.EducationType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

  @InjectMocks
  private MemberService memberService;

  @Mock
  private MemberRepository memberRepository;

  @Mock
  private BCryptPasswordEncoder passwordEncoder;

  @Mock
  private JwtProvider jwtProvider;

  private MemberRequest.SignUp signUpRequest;

  private MemberRequest.SignIn signInRequest;

  private Member member;

  @BeforeEach
  void setUp() {
    signUpRequest = MemberRequest.SignUp.builder()
        .name("test")
        .email("test@email.com")
        .password("!123Test")
        .birthDate(new Date())
        .education(EducationType.UNIVERSITY)
        .alarmEnabled(true)
        .build();

    signInRequest = MemberRequest.SignIn.builder()
        .email("test@email.com")
        .password("!123Test")
        .build();

    member = Member.builder()
        .id(1L)
        .email("test@email.com")
        .password("encoded-password")
        .name("test")
        .birthDate(new Date())
        .education(EducationType.UNIVERSITY)
        .alarmEnabled(true)
        .build();
  }

  @Test
  @DisplayName("회원가입 성공")
  void successSignUp() {
    // given
    when(memberRepository.findByEmail(signUpRequest.getEmail())).thenReturn(Optional.empty());
    when(passwordEncoder.encode(signUpRequest.getPassword())).thenReturn("password");

    Member savedMember = Member.builder()
        .id(1L)
        .name(signUpRequest.getName())
        .email(signUpRequest.getEmail())
        .password("password")
        .birthDate(signUpRequest.getBirthDate())
        .education(signUpRequest.getEducation())
        .alarmEnabled(signUpRequest.isAlarmEnabled())
        .build();

    when(memberRepository.save(any(Member.class))).thenReturn(savedMember);

    // when
    MemberResponse response = memberService.signUp(signUpRequest);

    // then
    assertEquals(savedMember.getId(), response.getId());
    assertEquals(savedMember.getName(), response.getName());
    assertEquals(savedMember.getEmail(), response.getEmail());
    assertEquals(savedMember.getBirthDate(), response.getBirthDate());
    assertEquals(savedMember.getEducation(), response.getEducation());
    assertEquals(savedMember.isAlarmEnabled(), response.isAlarmEnabled());
  }

  @Test
  @DisplayName("회원가입 실패 - 이메일 중복")
  void failSignUp_DuplicateEmail() {
    // given
    Member existingMember = Member.builder()
        .id(1L)
        .name("test")
        .email(signUpRequest.getEmail())
        .password("password")
        .birthDate(signUpRequest.getBirthDate())
        .education(signUpRequest.getEducation())
        .alarmEnabled(signUpRequest.isAlarmEnabled())
        .build();

    when(memberRepository.findByEmail(signUpRequest.getEmail()))
        .thenReturn(Optional.of(existingMember));

    // when & then
    CustomException exception = assertThrows(CustomException.class, () -> memberService.signUp(signUpRequest));

    assertEquals(ErrorCode.EMAIL_EXIST, exception.getErrorCode());
  }

  @Test
  @DisplayName("로그인 성공")
  void successSignIn() {
    // given
    when(memberRepository.findByEmail(signInRequest.getEmail()))
        .thenReturn(Optional.of(member));
    when(passwordEncoder.matches(signInRequest.getPassword(), member.getPassword()))
        .thenReturn(true);
    when(jwtProvider.createAccessToken(member.getId(), member.getEmail()))
        .thenReturn("access-token");
    when(jwtProvider.createRefreshToken(member.getId(), member.getEmail()))
        .thenReturn("refresh-token");

    // when
    TokenDto result = memberService.signIn(signInRequest);

    // then
    assertEquals("access-token", result.getAccessToken());
    assertEquals("refresh-token", result.getRefreshToken());
    verify(memberRepository).save(any(Member.class));  // refreshToken 저장 확인
  }

  @Test
  @DisplayName("로그인 실패 - 존재하지 않는 이메일")
  void failSignIn_emailNotFound() {
    // given
    when(memberRepository.findByEmail(signInRequest.getEmail()))
        .thenReturn(Optional.empty());

    // when & then
    CustomException exception = assertThrows(CustomException.class, () -> memberService.signIn(signInRequest));

    assertEquals(ErrorCode.LOGIN_FAIL, exception.getErrorCode());
  }
}