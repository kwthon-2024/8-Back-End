package kwthon.kwclub.review;

import kwthon.kwclub.com.team.project.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByClub_Id(Long clubId);
}
