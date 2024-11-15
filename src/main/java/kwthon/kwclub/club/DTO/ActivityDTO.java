package kwthon.kwclub.club.DTO;

import kwthon.kwclub.com.team.project.entity.Activity;
import kwthon.kwclub.com.team.project.entity.Club;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ActivityDTO {
    private Long id;
    private String date;
    private Club club;
    private String description;

    public static ActivityDTO from(Activity activity) {
        return ActivityDTO.builder()
                .id(activity.getId())
                .club(activity.getClub())
                .date(activity.getDate())
                .description(activity.getDescription())
                .build();
    }

    public static Activity toEntity(ActivityDTO activityDTO) {
        return Activity.builder()
                .id(activityDTO.getId())
                .club(activityDTO.getClub())
                .date(activityDTO.getDate())
                .description(activityDTO.getDescription())
                .build();
    }
}
