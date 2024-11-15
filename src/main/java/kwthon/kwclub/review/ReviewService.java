package kwthon.kwclub.review;

import kwthon.kwclub.com.team.project.entity.Review;
import kwthon.kwclub.review.DTO.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<ReviewDTO> getAllReviewsByClub(Long clubId) {
        List<Review> reviews = reviewRepository.findAllByClub_Id(clubId);
        return reviews.stream()
                .map(ReviewDTO::from)
                .toList();
    }

}
