package kwthon.kwclub.com.team.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;  // JpaRepository import
import java.util.Optional;  // Optional import
import kwthon.kwclub.com.team.project.entity.User;;  // User 클래스를 import (정확한 경로로 수정)

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
