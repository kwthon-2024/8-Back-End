package kwthon.kwclub.club;

import kwthon.kwclub.club.DTO.*;
import kwthon.kwclub.club.service.ActivityService;
import kwthon.kwclub.club.service.AnnouncementService;
import kwthon.kwclub.club.service.ClubService;
import kwthon.kwclub.club.service.ScheduleService;
import kwthon.kwclub.com.team.project.entity.Activity;
import kwthon.kwclub.com.team.project.entity.Announcement;
import kwthon.kwclub.com.team.project.entity.Club;
import kwthon.kwclub.com.team.project.entity.Schedule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class ClubServiceTest {

    @Autowired
    private ClubService clubService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private ScheduleService scheduleService;

    @Test
    @DisplayName("activity 추가 테스트")
    void addActivityTest() {
        //given
        Club club = Club.builder()
                .name("kw")
                .category("sports")
                .affiliation("central")
                .build();
        ClubRequestDTO clubDTO = clubService.createClub(ClubRequestDTO.from(club));
        ClubDTO savedClubDTO = clubService.getClubDetails(clubDTO.getId());
        Activity activity1 = Activity.builder()
                .club(ClubDTO.toEntity(savedClubDTO))
                .date("20241115")
                .description("online meeting")
                .build();
        Activity activity2 = Activity.builder()
                .club(ClubDTO.toEntity(savedClubDTO))
                .date("20241117")
                .description("offline meeting")
                .build();

        //when
        activityService.createActivity(ActivityDTO.from(activity1));
        activityService.createActivity(ActivityDTO.from(activity2));

        List<ActivityDTO> foundActivities = activityService.getAllActivities(clubDTO.getId());

        //then
        assertThat(foundActivities.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("Announcement 추가 테스트")
    void addAnnouncementTest() {
        //given
        Club club = Club.builder()
                .name("kw")
                .affiliation("kwClub")
                .category("sports")
                .build();
        ClubRequestDTO clubDTO = clubService.createClub(ClubRequestDTO.from(club));
        ClubDTO savedClubDTO = clubService.getClubDetails(clubDTO.getId());
        Announcement announcement1 = Announcement.builder()
                .content("content1")
                .title("title1")
                .date("date1")
                .club(ClubDTO.toEntity(savedClubDTO))
                .build();
        Announcement announcement2 = Announcement.builder()
                .content("content2")
                .title("title2")
                .date("date2")
                .club(ClubDTO.toEntity(savedClubDTO))
                .build();

        //when
        announcementService.createAnnouncement(AnnouncementDTO.from(announcement1));
        announcementService.createAnnouncement(AnnouncementDTO.from(announcement2));
        List<AnnouncementDTO> foundAnnouncements = announcementService.getAllAnnouncements(clubDTO.getId());

        //then
        assertThat(foundAnnouncements.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("schedule 추가 테스트")
    void addScheduleTest() {
        //given
        Club club = Club.builder()
                .name("kw")
                .affiliation("kwClub")
                .category("sports")
                .build();
        ClubRequestDTO clubDTO = clubService.createClub(ClubRequestDTO.from(club));
        ClubDTO savedClubDTO = clubService.getClubDetails(clubDTO.getId());
        Schedule schedule1 = Schedule.builder()
                .location("location1")
                .eventName("eventName1")
                .eventDate("eventDate1")
                .club(ClubDTO.toEntity(savedClubDTO))
                .build();
        Schedule schedule2 = Schedule.builder()
                .location("location2")
                .eventName("eventName2")
                .eventDate("eventDate2")
                .club(ClubDTO.toEntity(savedClubDTO))
                .build();

        //when
        scheduleService.createSchedule(ScheduleDTO.from(schedule1));
        scheduleService.createSchedule(ScheduleDTO.from(schedule2));
        List<ScheduleDTO> foundSchedules = scheduleService.getAllSchedules(clubDTO.getId());

        //then
        assertThat(foundSchedules.size()).isEqualTo(2);
    }
}
