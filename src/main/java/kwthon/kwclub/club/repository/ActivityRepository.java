package kwthon.kwclub.club.repository;

import kwthon.kwclub.com.team.project.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByClub_Id(Long clubId);
}
