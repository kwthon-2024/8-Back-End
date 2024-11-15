package kwthon.kwclub.com.team.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_clubs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserClub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @Column(nullable = false)
    private String role; // 동아리 내 역할 (예: 회장, 부회장, 회원)

    @Column(nullable = false)
    private boolean isApproved; // 가입 승인 여부
}
