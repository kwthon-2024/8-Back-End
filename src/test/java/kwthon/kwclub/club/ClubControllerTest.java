//package kwthon.kwclub.club;
//
//import kwthon.kwclub.club.DTO.ClubDTO;
//import kwthon.kwclub.club.service.ActivityService;
//import kwthon.kwclub.club.service.AnnouncementService;
//import kwthon.kwclub.club.service.ClubService;
//import kwthon.kwclub.club.service.ScheduleService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ClubControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ClubService clubService;
//
//    @Test
//    @DisplayName("동아리 사진 업로드 테스트")
//    void uploadPhotoTest() throws Exception {
//        // given: ClubDTO로 동아리 생성
////        ClubDTO.builder()
////                .name("sports")
////                .category("study")
////
//        ClubDTO clubDTO = clubService.createClub(new ClubDTO("kw", "sports", "kwClub description"));
//
//        // 파일 생성
//        MockMultipartFile file = new MockMultipartFile(
//                "file",
//                "test-image.jpg",
//                MediaType.IMAGE_JPEG_VALUE,
//                "Test image content".getBytes()
//        );
//
//        // when & then: 사진 업로드 요청
//        mockMvc.perform(multipart("/api/club/" + clubDTO.getId() + "/photo")
//                        .file(file))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Photo uploaded successfully. Club updated: " + clubDTO.getName()));
//    }
//
//    @Test
//    @DisplayName("동아리 사진 조회 테스트")
//    void getPhotoTest() throws Exception {
//        // given: ClubDTO로 동아리 생성 및 사진 파일 경로 설정
//        ClubDTO clubDTO = clubService.createClub(new ClubDTO("kw", "sports", "kwClub description"));
//        Path imagePath = Paths.get("uploads/clubs/" + clubDTO.getId() + "/test-image.jpg");
//        Files.createDirectories(imagePath.getParent());
//        Files.write(imagePath, "Test image content".getBytes());
//
//        // when & then: 사진 조회 요청
//        mockMvc.perform(get("/api/club/" + clubDTO.getId() + "/photo"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
//                .andExpect(content().bytes(Files.readAllBytes(imagePath)));
//    }
//}
