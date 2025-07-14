package lion.studypartner.domain.member.dto;

import java.time.LocalDateTime;
import java.util.Date;
import lion.studypartner.domain.member.entity.Member;
import lion.studypartner.global.type.EducationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

  private Long id;
  private String name;
  private String email;
  private Date birthDate;
  private boolean alarmEnabled;
  private String imageUrl;
  private EducationType education;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  public static MemberResponse from(Member member) {
    return MemberResponse.builder()
        .id(member.getId())
        .name(member.getName())
        .email(member.getEmail())
        .birthDate(member.getBirthDate())
        .alarmEnabled(member.isAlarmEnabled())
        .imageUrl(member.getImageUrl())
        .education(member.getEducation())
        .createdAt(member.getCreatedAt())
        .updatedAt(member.getUpdatedAt())
        .deletedAt(member.getDeletedAt())
        .build();
  }
}
