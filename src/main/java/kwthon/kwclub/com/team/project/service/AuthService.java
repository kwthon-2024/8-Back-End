package kwthon.kwclub.com.team.project.service;

import jakarta.persistence.EntityNotFoundException;
import kwthon.kwclub.com.team.project.DTO.UserLoginDTO;
import kwthon.kwclub.com.team.project.entity.User;
import kwthon.kwclub.com.team.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public String authenticate(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByStudentId(userLoginDTO.getStudentId())
                .orElseThrow(
                        () -> new EntityNotFoundException("user not found")
                );
        return user.getName();
    }


}
