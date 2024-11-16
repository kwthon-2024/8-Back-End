package kwthon.kwclub.club.repository;

import kwthon.kwclub.com.team.project.entity.Club;
import kwthon.kwclub.com.team.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {

    List<Club> findAllByCategory(String category);


}
