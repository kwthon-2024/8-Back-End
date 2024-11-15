package kwthon.kwclub.club.repository;

import kwthon.kwclub.com.team.project.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAllByClub_Id(Long clubId);
}
