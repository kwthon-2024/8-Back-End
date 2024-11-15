package kwthon.kwclub.club.service;

import kwthon.kwclub.club.DTO.ActivityDTO;
import kwthon.kwclub.club.repository.ActivityRepository;
import kwthon.kwclub.com.team.project.entity.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    @Transactional
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        Activity activity = Activity.builder()
                .club(activityDTO.getClub())
                .date(activityDTO.getDate())
                .description(activityDTO.getDescription())
                .build();
        return ActivityDTO.from(activityRepository.save(activity));
    }

    public List<ActivityDTO> getAllActivities(Long clubId) {
        return activityRepository.findAllByClub_Id(clubId)
                .stream()
                .map(ActivityDTO::from)
                .toList();
    }
}
