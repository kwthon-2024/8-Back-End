package kwthon.kwclub.club.repository;

import kwthon.kwclub.com.team.project.entity.UserClub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserClubRepository extends JpaRepository<UserClub, Long> {
    List<UserClub> findAllByUser_Id(Long userId);
    List<UserClub> findAllByClub_Id(Long clubId);
    List<UserClub> findAllByClub_IdAndIsApproved(Long clubId, boolean isApproved);
    List<UserClub> findAllByUser_IdAndIsApproved(Long userId, boolean isApproved);
}
