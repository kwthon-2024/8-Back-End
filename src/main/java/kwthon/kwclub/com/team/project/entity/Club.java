package kwthon.kwclub.com.team.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "clubs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column
    private String description;

    @Column
    private String filePath; // 동아리 사진 경로

    @Column(nullable = false)
    private boolean isCentral; // 중앙동아리 여부

    @OneToMany(mappedBy = "club")
    private List<UserClub> userClubs; // Club과 User의 중간 엔티티

    @Column(nullable = false)
    private boolean isRecruiting; // 모집 중 여부
}
