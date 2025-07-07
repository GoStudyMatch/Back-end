# 스터디 파트너 앱

스터디 파트너 앱은 사용자들이 스터디 그룹을 찾고 관리할 수 있는 플랫폼입니다.

![멋사-backend](https://github.com/user-attachments/assets/a8890b22-3955-48dc-bae4-ca7d695290f4)


---

## 주요 기능

### 1. 인증
- 로그인 (`login_screen.dart`)
  - 이메일/비밀번호 로그인
  - 소셜 로그인 (추후 구현 예정)

- 회원가입 (`register_screen.dart`)
  - 닉네임, 생년월일, 이메일, 비밀번호 입력
  - 최종학력 및 재학상태 선택
  - 이용약관 동의

### 2. 홈 화면 (`home_screen.dart`)
- 현재 위치 기반 스터디 검색
- 내 스터디 목록
- 추천 스터디 목록
- 스터디 생성 기능

### 3. 프로필 관리 (`profile_screen.dart`)
- 기본 정보 표시
- 보유 기술 관리
- 자기소개
- 프로필 수정
- 로그아웃

### 4. 스터디 관리
- 스터디 검색 (`study_search_screen.dart`)
  - 카테고리, 지역, 시간대별 필터링
  - 검색 결과 표시

- 스터디 상세 (`study_detail_screen.dart`)
  - 스터디 정보 표시
  - 참여 신청 기능
  - 멤버 목록

### 5. 커뮤니케이션
- 채팅 (`chat_screen.dart`, `chat_room_screen.dart`)
  - 채팅방 목록
  - 실시간 메시지 전송
  - 이미지 공유

- 알림 (`notification_screen.dart`)
  - 스터디 관련 알림
  - 메시지 알림
  - 시스템 알림

### 6. 부가 기능
- 지도 (`map_screen.dart`)
  - 스터디 위치 표시
  - 주변 스터디 검색

- 학습 목표 (`todo_screen.dart`)
  - 목표 설정
  - 진행 상황 추적

- 팔로우/팔로잉 (`follow_screen.dart`)
  - 사용자 팔로우
  - 팔로워/팔로잉 목록

## 기술 스택

<div align="center">
  <table>
    <tr>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white" alt="Java"><br />Java
      </td>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"><br />Spring Boot
      </td>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white" alt="MariaDB"><br />MariaDB
      </td>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white" alt="Gradle"><br />Gradle
      </td>
    </tr>
    <tr>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/jpa-%23323330.svg?style=for-the-badge" alt="JPA"><br />JPA
      </td>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens" alt="JWT"><br />JWT
      </td>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/spring--security-6DB33F?style=for-the-badge&logo=spring%20security&logoColor=white" alt="Spring Security"><br />Spring Security
      </td>
            <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/AWS%20S3-569A31?style=for-the-badge&logo=amazonaws&logoColor=white" alt="AWS S3"><br />AWS S3
      </td>
    </tr>
    <tr>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/OAuth2-3A3A3A?style=for-the-badge&logo=oauth" alt="OAuth2 Client"><br />OAuth2 Client
      </td>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="JUnit"><br />JUnit
      </td>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/Mockito-6DB33F?style=for-the-badge&logo=java&logoColor=white" alt="Mockito"><br />Mockito
      </td>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white" alt="Redis"><br />Redis
      </td>
    </tr>
    <tr>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/-ElasticSearch-005571?style=for-the-badge&logo=elasticsearch" alt="ElasticSearch"><br />ElasticSearch
      </td>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/swagger-%2385EA2D.svg?style=for-the-badge&logo=swagger&logoColor=black" alt="Swagger"><br />Swagger
      </td>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white" alt="AWS"><br />AWS
      </td>
      <td align="center" style="padding: 10px;">
        <img src="https://img.shields.io/badge/AWS%20EC2-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white" alt="AWS EC2"><br />AWS EC2
      </td>
    </tr>
  </table>
</div>
<br>

## 개발 환경

- Spring : 3.5.3
- Java : 17
- Intellij

## 라이선스

이 프로젝트는 MIT 라이선스를 따릅니다.
