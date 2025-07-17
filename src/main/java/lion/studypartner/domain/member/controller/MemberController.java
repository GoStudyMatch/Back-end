package lion.studypartner.domain.member.controller;

import jakarta.validation.Valid;
import lion.studypartner.domain.member.dto.MemberRequest;
import lion.studypartner.domain.member.dto.MemberResponse;
import lion.studypartner.domain.member.service.MemberService;
import lion.studypartner.global.security.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/members/signup")
  public ResponseEntity<MemberResponse> signUp(@Valid @RequestBody MemberRequest.SignUp request) {

    return ResponseEntity.ok(memberService.signUp(request));
  }

  @PostMapping("/members/signin")
  public ResponseEntity<TokenDto> signIn(@Valid @RequestBody MemberRequest.SignIn request) {

    return ResponseEntity.ok(memberService.signIn(request));
  }
}
