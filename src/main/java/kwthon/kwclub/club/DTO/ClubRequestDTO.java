package kwthon.kwclub.club.DTO;

import kwthon.kwclub.com.team.project.entity.Club;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClubRequestDTO {
    private Long id;
    private String name;
    private String category;
    private String affiliation;

    public static ClubRequestDTO from(Club club) {
        return ClubRequestDTO.builder()
                .id(club.getId())
                .name(club.getName())
                .category(club.getCategory())
                .affiliation(club.getAffiliation())
                .build();
    }

    public static Club toEntity(ClubRequestDTO clubRequestDTO) {
        return Club.builder()
                .id(clubRequestDTO.getId())
                .name(clubRequestDTO.getName())
                .category(clubRequestDTO.getCategory())
                .affiliation(clubRequestDTO.getAffiliation())
                .build();
    }
}
