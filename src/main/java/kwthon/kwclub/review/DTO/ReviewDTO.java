package kwthon.kwclub.review.DTO;


import kwthon.kwclub.com.team.project.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewDTO {

    private Long id;
    private String content;

    public static ReviewDTO from(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .content(review.getContent())
                .build();
    }

}
