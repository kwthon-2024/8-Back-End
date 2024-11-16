package kwthon.kwclub.review;

import jakarta.persistence.EntityNotFoundException;
import kwthon.kwclub.club.repository.ClubRepository;
import kwthon.kwclub.com.team.project.entity.Club;
import kwthon.kwclub.com.team.project.entity.Review;
import kwthon.kwclub.com.team.project.entity.User;
import kwthon.kwclub.com.team.project.repository.UserRepository;
import kwthon.kwclub.review.DTO.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    public List<ReviewDTO> getAllReviewsByClub(Long clubId) {
        List<Review> reviews = reviewRepository.findAllByClub_Id(clubId);
        return reviews.stream()
                .map(ReviewDTO::from)
                .toList();
    }

    public void addReview(Long clubId, String content, String name) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("club not found"));
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
        Review review = Review.builder()
                .content(content)
                .club(club)
                .user(user)
                .build();
        Review savedReview = reviewRepository.save(review);


    }

}
