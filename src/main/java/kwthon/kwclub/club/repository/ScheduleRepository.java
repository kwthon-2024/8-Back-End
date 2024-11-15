package kwthon.kwclub.club.repository;

import kwthon.kwclub.com.team.project.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByClub_Id(Long clubId);
}
