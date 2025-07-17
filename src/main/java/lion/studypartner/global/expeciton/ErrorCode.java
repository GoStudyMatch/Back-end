package lion.studypartner.global.expeciton;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  EMAIL_EXIST("이미 가입된 메일입니다.", HttpStatus.BAD_REQUEST),
  LOGIN_FAIL("이메일, 비밀번호를 확인해 주세요.", HttpStatus.BAD_REQUEST);

  private final String message;
  private final HttpStatus status;
}
