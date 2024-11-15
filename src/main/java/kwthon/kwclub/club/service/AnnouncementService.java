package kwthon.kwclub.club.service;

import kwthon.kwclub.club.DTO.AnnouncementDTO;
import kwthon.kwclub.club.repository.AnnouncementRepository;
import kwthon.kwclub.com.team.project.entity.Announcement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Transactional
    public AnnouncementDTO createAnnouncement(AnnouncementDTO announcementDTO) {
        Announcement announcement = Announcement.builder()
                .title(announcementDTO.getTitle())
                .club(announcementDTO.getClub())
                .date(announcementDTO.getDate())
                .content(announcementDTO.getContent())
                .build();
        return AnnouncementDTO.from(announcementRepository.save(announcement));
    }

    public List<AnnouncementDTO> getAllAnnouncements(Long clubId) {
        return announcementRepository.findAllByClub_Id(clubId)
                .stream()
                .map(AnnouncementDTO::from)
                .toList();
    }
}
