package kwthon.kwclub.club.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClubDetailDTO {
    private Long id;
    private String name;
    private String affiliation;
}
