package kwthon.kwclub.com.team.project.controller;

import kwthon.kwclub.com.team.project.DTO.UserRequestDTO;
import kwthon.kwclub.com.team.project.entity.User;
import kwthon.kwclub.com.team.project.service.UserService;
import kwthon.kwclub.com.team.project.token.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")

    public ResponseEntity<String> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            userService.signUp(userRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패" + e.getMessage());
        }
    }

//    @GetMapping("/details")
//    public ResponseEntity<String> getUserDetails(@PathVariable Long userId) {
//        User user = userService.getUserById(userId);
//
//    }
}
