package kwthon.kwclub.club.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kwthon.kwclub.com.team.project.entity.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ClubDTO {
    private Long id;
    private String name;
    private String category;
    private String description;

    public static ClubDTO from(Club club) {
        return ClubDTO.builder()
                .id(club.getId())
                .name(club.getName())
                .category(club.getCategory())
                .description(club.getDescription())
                .build();
    }

    public static Club toEntity(ClubDTO clubDTO) {
        return Club.builder()
                .id(clubDTO.getId())
                .name(clubDTO.getName())
                .category(clubDTO.getCategory())
                .description(clubDTO.getDescription())
                .build();
    }
}
