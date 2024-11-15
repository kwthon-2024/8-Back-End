package kwthon.kwclub.club.DTO;

import kwthon.kwclub.com.team.project.entity.Announcement;
import kwthon.kwclub.com.team.project.entity.Club;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AnnouncementDTO {
    private Long id;
    private Club club;
    private String title;
    private String content;
    private String date;

    public static AnnouncementDTO from(Announcement announcement) {
        return AnnouncementDTO.builder()
                .id(announcement.getId())
                .club(announcement.getClub())
                .title(announcement.getTitle())
                .content(announcement.getContent())
                .date(announcement.getDate())
                .build();
    }

    public static Announcement toEntity(AnnouncementDTO announcementDTO) {
        return Announcement.builder()
                .id(announcementDTO.getId())
                .club(announcementDTO.getClub())
                .title(announcementDTO.getTitle())
                .content(announcementDTO.getContent())
                .date(announcementDTO.getDate())
                .build();
    }
}
