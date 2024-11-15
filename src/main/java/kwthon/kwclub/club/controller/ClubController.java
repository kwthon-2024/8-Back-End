package kwthon.kwclub.club.controller;

import kwthon.kwclub.club.DTO.ClubDTO;
import kwthon.kwclub.club.service.ActivityService;
import kwthon.kwclub.club.service.AnnouncementService;
import kwthon.kwclub.club.service.ClubService;
import kwthon.kwclub.club.service.ScheduleService;
import kwthon.kwclub.com.team.project.entity.Club;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/club")
public class ClubController {

    private final ClubService clubService;
    private final ActivityService activityService;
    private final AnnouncementService announcementService;
    private final ScheduleService scheduleService;

    @GetMapping("/{clubId}")
    public ResponseEntity<ClubDTO> getClubDetails(@PathVariable Long clubId) {
        ClubDTO clubDTO = clubService.getClubDetails(clubId);

        if (clubDTO != null) {
            return ResponseEntity.ok(clubDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<ClubDTO>> getClubsByCategory(@PathVariable String category) {
        List<ClubDTO> clubsByCategory = clubService.getClubsByCategory(category);

        if (clubsByCategory != null) {
            return ResponseEntity.ok(clubsByCategory);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/{clubId}/photo")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long clubId, @RequestParam("file") MultipartFile file) {
        ClubDTO updatedClub = clubService.uploadClubPhoto(clubId, file);
        return ResponseEntity.ok("Photo uploaded successfully. Club updated: " + updatedClub.getName());
    }

    @GetMapping("/{clubId}/photo")
    public ResponseEntity<Resource> getPhoto(@PathVariable Long clubId) {
        ClubDTO clubDTO = clubService.getClubDetails(clubId); // 동아리 정보 조회
        String photoPath = clubDTO.getFilePath();

        if (photoPath == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        try {
            Path path = Paths.get(photoPath);
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            // 파일 타입을 동적으로 설정
            MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(path));

            return ResponseEntity.ok()
                    .contentType(mediaType != null ? mediaType : MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
