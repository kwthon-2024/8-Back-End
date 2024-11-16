package kwthon.kwclub.com.team.project.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDetailsDTO {
    private Long id;
    private String studentId;
    private String name;
    private String department;
}
