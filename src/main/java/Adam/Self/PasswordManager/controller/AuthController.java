package Adam.Self.PasswordManager.controller;


import Adam.Self.PasswordManager.dto.UserLoginDtop;
import Adam.Self.PasswordManager.dto.UserRegistrationDtop;
import Adam.Self.PasswordManager.model.User;
import Adam.Self.PasswordManager.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationDtop dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPasswordHash(dto.getPassword());

        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDtop userLoginDtop){


        User existingUser = userService.findByEmail(userLoginDtop.getEmail());

        if (existingUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
        }

        if (!userLoginDtop.getPassword().equals(existingUser.getPasswordHash())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
        }

        existingUser.setPasswordHash(null);
        return ResponseEntity.ok(existingUser);
    }
}