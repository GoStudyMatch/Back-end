package lion.studypartner.domain.member.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lion.studypartner.global.security.TokenDto;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Date;
import lion.studypartner.domain.member.dto.MemberRequest;
import lion.studypartner.domain.member.dto.MemberResponse;
import lion.studypartner.domain.member.service.MemberService;
import lion.studypartner.global.type.EducationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WithMockUser
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private MemberService memberService;

  private MemberRequest.SignUp signUpRequest;
  private MemberResponse memberResponse;
  private MemberRequest.SignIn signInRequest;

  @BeforeEach
  void setUp() {
    this.signUpRequest = MemberRequest.SignUp.builder()
        .name("test")
        .email("test@email.com")
        .password("!123Test")
        .birthDate(new Date())
        .education(EducationType.UNIVERSITY)
        .alarmEnabled(true)
        .build();

    this.memberResponse = MemberResponse.builder()
        .id(1L)
        .name("test")
        .email("test@email.com")
        .birthDate(new Date())
        .alarmEnabled(true)
        .imageUrl("test.url")
        .education(EducationType.UNIVERSITY)
        .createdAt(LocalDateTime.of(2023, 1, 1, 10, 0))
        .updatedAt(LocalDateTime.of(2023, 1, 1, 10, 0))
        .deletedAt(null)
        .build();

    signInRequest = MemberRequest.SignIn.builder()
        .email("test@email.com")
        .password("!123Test")
        .build();
  }

  @Test
  @DisplayName("회원가입 성공")
  void successSignUp() throws Exception {
    // given
    given(memberService.signUp(any(MemberRequest.SignUp.class)))
        .willReturn(memberResponse);

    // when & then
    mockMvc.perform(post("/api/members/signup")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(signUpRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.name").value("test"))
        .andExpect(jsonPath("$.email").value("test@email.com"))
        .andExpect(jsonPath("$.alarmEnabled").value(true))
        .andExpect(jsonPath("$.imageUrl").value("test.url"))
        .andExpect(jsonPath("$.education").value("UNIVERSITY"))
        .andExpect(jsonPath("$.createdAt").value("2023-01-01T10:00:00"))
        .andExpect(jsonPath("$.updatedAt").value("2023-01-01T10:00:00"))
        .andExpect(jsonPath("$.deletedAt").doesNotExist())
        .andDo(print());
  }

  @Test
  @DisplayName("회원가입 실패 - 유효하지 않은 이메일")
  void failSignUp() throws Exception {
    // given
    signUpRequest = MemberRequest.SignUp.builder()
        .name("test")
        .email("FALSE-email")
        .password("!123Test")
        .birthDate(new Date())
        .education(EducationType.UNIVERSITY)
        .alarmEnabled(true)
        .build();

    // when & then
    mockMvc.perform(post("/api/members/signup")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(signUpRequest)))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }

  @Test
  @DisplayName("로그인 성공")
  void successSignIn() throws Exception {
    // given
    TokenDto tokenDto = TokenDto.builder()
        .accessToken("access-token")
        .refreshToken("refresh-token")
        .build();

    given(memberService.signIn(any(MemberRequest.SignIn.class))).willReturn(tokenDto);

    // when & then
    mockMvc.perform(post("/api/members/signin")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(signInRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.accessToken").value("access-token"))
        .andExpect(jsonPath("$.refreshToken").value("refresh-token"))
        .andDo(print());
  }


  @Test
  @DisplayName("로그인 실패 - 비밀번호 누락")
  void failSignIn_PasswordNull() throws Exception {

    //given
    signInRequest = MemberRequest.SignIn.builder()
        .email("test@email.com")
        .password(null)  // 비밀번호 누락
        .build();

    // when & then
    mockMvc.perform(post("/api/members/signin")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(signInRequest)))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }
}
