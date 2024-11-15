package kwthon.kwclub.review.DTO;


import kwthon.kwclub.com.team.project.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDTO {

    private Long id;
    private String content;
    private int rating;

    public static ReviewDTO from(Review review) {
        return new ReviewDTO(review.getId(), review.getContent(), review.getRating());
    }
}
