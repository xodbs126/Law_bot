package Lawbot.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRequest userRequest) {
        User user = userService.registerUser(userRequest.username, userRequest.password, userRequest.email, userRequest.role);
        return ResponseEntity.ok(user);
    }

    public static class UserRequest {
        public String username;
        public String password;
        public String email;
        public User.UserRole role;
    }
}
