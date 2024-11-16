package kwthon.kwclub.com.team.project.controller;

import jakarta.persistence.EntityNotFoundException;
import kwthon.kwclub.com.team.project.DTO.UserLoginDTO;
import kwthon.kwclub.com.team.project.service.AuthService;
import kwthon.kwclub.com.team.project.service.UserService;
import kwthon.kwclub.com.team.project.token.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            String name = authService.authenticate(userLoginDTO);
            String token = jwtUtil.generateToken(name);
            return ResponseEntity.ok(token);
        } catch (EntityNotFoundException e) {
            System.out.println("-------" + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("학번 또는 비밀번호가 잘못되었습니다.");
        } catch (Exception e) {
            System.out.println("------" + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패.");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", ""); // Bearer 제거
        if (jwtUtil.isTokenValid(jwt)) {
            String name = jwtUtil.extractName(jwt); // 토큰에서 사용자 이름 추출
            return ResponseEntity.ok(name);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
    }
}
