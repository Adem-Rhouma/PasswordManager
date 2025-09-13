package Adam.Self.PasswordManager.controller;


import Adam.Self.PasswordManager.model.User;
import Adam.Self.PasswordManager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        User existingUser = userService.findByEmail(user.getEmail());

        if (existingUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
        }

        if (!user.getPasswordHash().equals(existingUser.getPasswordHash())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
        }

        existingUser.setPasswordHash(null);
        return ResponseEntity.ok(existingUser);
    }
}