package kwthon.kwclub.club.controller;

import kwthon.kwclub.club.DTO.ClubDTO;
import kwthon.kwclub.club.DTO.ClubDetailDTO;
import kwthon.kwclub.club.DTO.ClubRequestDTO;
import kwthon.kwclub.club.service.ActivityService;
import kwthon.kwclub.club.service.AnnouncementService;
import kwthon.kwclub.club.service.ClubService;
import kwthon.kwclub.club.service.ScheduleService;
import kwthon.kwclub.com.team.project.entity.Club;
import kwthon.kwclub.com.team.project.token.JwtUtil;
import kwthon.kwclub.review.DTO.ReviewDTO;
import kwthon.kwclub.review.ReviewService;
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
@CrossOrigin(origins = "http://localhost:3000")
public class ClubController {

    private final ClubService clubService;
    private final ActivityService activityService;
    private final AnnouncementService announcementService;
    private final ScheduleService scheduleService;
    private final ReviewService reviewService;
    private final JwtUtil jwtUtil;

    @GetMapping("/{clubId}/details")
    public ResponseEntity<ClubDetailDTO> getClubDetails(@PathVariable Long clubId) {
        ClubDTO clubDTO = clubService.getClubDetails(clubId);
        ClubDetailDTO clubDetailDTO = ClubDetailDTO.builder()
                .name(clubDTO.getName())
                .affiliation(clubDTO.getAffiliation())
                .build();

        if (clubDTO != null) {
            return ResponseEntity.ok(clubDetailDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{clubId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long clubId) {
        List<ReviewDTO> reviews = reviewService.getAllReviewsByClub(clubId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/{clubId}/reviews")
    public ResponseEntity<String> createReviews(@PathVariable Long clubId,
                                                @RequestHeader("Authorization") String token,
                                                @RequestBody ReviewDTO reviewDTO) {
        try {
            String jwtToken = token.replace("Bearer ", "");
            String name = jwtUtil.extractName(jwtToken);

            reviewService.addReview(clubId, reviewDTO.getContent(), name);
            return ResponseEntity.status(HttpStatus.CREATED).body("리뷰 등록 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("리뷰 생성 실패 " + e.getMessage());
        }
    }
    @PostMapping("/")
    public ResponseEntity<String> createClub(@RequestBody ClubRequestDTO clubRequestDTO) {
        clubService.createClub(clubRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


    @GetMapping("/{category}")
    public ResponseEntity<List<ClubDTO>> getClubsByCategory(@PathVariable String category) {
        List<ClubDTO> clubsByCategory = clubService.getClubsByCategory(category);
        System.out.println("------------" + clubsByCategory);
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
