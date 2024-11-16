package kwthon.kwclub.com.team.project.DTO;

import kwthon.kwclub.com.team.project.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserRequestDTO {

    private Long id;
    private String studentId;
    private String name;
    private String password;
    private String email;
    private int age;
    private String phoneNumber;
    private String department;

    public static UserRequestDTO from(User user) {
        return UserRequestDTO.builder()
                .id(user.getId())
                .studentId(user.getStudentId())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .department(user.getDepartment())
                .build();
    }
    public static User toEntity(UserRequestDTO userRequestDTO) {
        return User.builder()
                .id(userRequestDTO.getId())
                .studentId(userRequestDTO.getStudentId())
                .name(userRequestDTO.getName())
                .password(userRequestDTO.getPassword())
                .email(userRequestDTO.getEmail())
                .age(userRequestDTO.getAge())
                .phoneNumber(userRequestDTO.getPhoneNumber())
                .department(userRequestDTO.getDepartment())
                .build();
    }
}
