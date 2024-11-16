package kwthon.kwclub.club.service;

import jakarta.persistence.EntityNotFoundException;
import kwthon.kwclub.club.DTO.*;
import kwthon.kwclub.club.repository.*;
import kwthon.kwclub.com.team.project.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubService {

    private final ClubRepository clubRepository;
    private final ActivityRepository activityRepository;
    private final AnnouncementRepository announcementRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserClubRepository userClubRepository;

    private final String UPLOAD_DIR = "uploads/clubs/";
    public List<ClubDTO> getClubsByCategory(String category) {
        return clubRepository.findAllByCategory(category)
                .stream()
                .map(ClubDTO::from)
                .toList();
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
    public ClubRequestDTO createClub(ClubRequestDTO clubRequestDTO) {
        Club club = Club.builder()
                .name(clubRequestDTO.getName())
                .category(clubRequestDTO.getCategory())
                .affiliation(clubRequestDTO.getAffiliation())
                .build();
        Club savedClub = clubRepository.save(club);
        return ClubRequestDTO.from(club);
    }

    @Transactional
    public ClubDTO uploadClubPhoto(Long clubId, MultipartFile file) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("Club with id " + clubId + " not found"));

        try {
            // 1. 고유 파일 이름 생성
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String clubDir = UPLOAD_DIR + clubId;

            // 2. 디렉토리 생성
            File directory = new File(clubDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 3. 파일 저장
            String filePath = clubDir + "/" + fileName;
            file.transferTo(new File(filePath));

            // 4. Club 엔티티의 photoPath 업데이트
            club.setFilePath(filePath);
            clubRepository.save(club);

            return ClubDTO.from(club);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload photo", e);
        }
    }

    @Transactional
    public UserClub requestJoinClub(User user, Club club, String role) {
        UserClub userClub = new UserClub();
        userClub.setClub(club);
        userClub.setUser(user);
        userClub.setRole(role);
        userClub.setApproved(false);

        return userClubRepository.save(userClub);
    }

    @Transactional
    public UserClub approveJoinRequest(Long userClubId) {
        UserClub userClub = userClubRepository.findById(userClubId)
                .orElseThrow(
                        () -> new EntityNotFoundException("userClub not found")
                );
        return null;
    }

}
