package kwthon.kwclub.com.team.project.service;
import jakarta.persistence.EntityNotFoundException;
import kwthon.kwclub.com.team.project.DTO.UserRequestDTO;
import kwthon.kwclub.com.team.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import kwthon.kwclub.com.team.project.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void signUp(UserRequestDTO userRequestDTO) {
        userRepository.save(UserRequestDTO.toEntity(userRequestDTO));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user not found")
        );
    }

}
