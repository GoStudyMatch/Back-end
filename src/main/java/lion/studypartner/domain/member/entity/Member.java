package lion.studypartner.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import lion.studypartner.domain.member.dto.MemberRequest;
import lion.studypartner.global.type.EducationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("deleted_at IS NULL")
@SQLDelete(sql = "UPDATE member SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
public class Member {

  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  private String name;

  @Setter
  private String email;

  @Setter
  private String password;
  private Date birthDate;

  @Setter
  private boolean alarmEnabled;

  @Setter
  private String imageUrl;

  @Setter
  private EducationType education;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  private LocalDateTime deletedAt;

  @Setter
  private String refreshToken;

  public static Member from(MemberRequest.SignUp request) {
    return Member.builder()
        .name(request.getName())
        .email(request.getEmail())
        .birthDate(request.getBirthDate())
        .alarmEnabled(request.isAlarmEnabled())
        .build();
  }
}
