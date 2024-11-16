package kwthon.kwclub.com.team.project.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class UserLoginDTO {
    private String studentId;
    private String password;
}
