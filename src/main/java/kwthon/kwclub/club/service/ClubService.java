package kwthon.kwclub.club.service;

import jakarta.persistence.EntityNotFoundException;
import kwthon.kwclub.club.DTO.ActivityDTO;
import kwthon.kwclub.club.DTO.AnnouncementDTO;
import kwthon.kwclub.club.DTO.ClubDTO;
import kwthon.kwclub.club.DTO.ScheduleDTO;
import kwthon.kwclub.club.repository.ActivityRepository;
import kwthon.kwclub.club.repository.AnnouncementRepository;
import kwthon.kwclub.club.repository.ClubRepository;
import kwthon.kwclub.club.repository.ScheduleRepository;
import kwthon.kwclub.com.team.project.entity.Activity;
import kwthon.kwclub.com.team.project.entity.Announcement;
import kwthon.kwclub.com.team.project.entity.Club;
import kwthon.kwclub.com.team.project.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubService {

    private final ClubRepository clubRepository;
    private final ActivityRepository activityRepository;
    private final AnnouncementRepository announcementRepository;
    private final ScheduleRepository scheduleRepository;

    public List<Club> getClubsByCategory(String category) {
        return clubRepository.findAllByCategory(category);
    }

    public ClubDTO getClubDetails(Long clubId) {
        return ClubDTO.from(clubRepository.findById(clubId).orElseThrow(
                () -> new EntityNotFoundException("club with id " + clubId + " not found")
        ));
    }

//    public List<Club> getAllClubByUser(Long userId) {
//        return clubRepository.findAllByUser(userId);
//    }

    @Transactional
    public ClubDTO createClub(ClubDTO clubDTO) {
        Club club = Club.builder()
                .name(clubDTO.getName())
                .category(clubDTO.getCategory())
                .description(clubDTO.getDescription())
                .build();
        Club savedClub = clubRepository.save(club);
        return ClubDTO.from(savedClub);
    }


}
