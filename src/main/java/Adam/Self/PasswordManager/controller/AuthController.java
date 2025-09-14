package Adam.Self.PasswordManager.controller;


import Adam.Self.PasswordManager.dto.AuthResponse;
import Adam.Self.PasswordManager.dto.UserLoginDtop;
import Adam.Self.PasswordManager.dto.UserRegistrationDtop;
import Adam.Self.PasswordManager.model.User;
import Adam.Self.PasswordManager.security.JwtUtil;
import Adam.Self.PasswordManager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService,  JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationDtop dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

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

        if (!passwordEncoder.matches(userLoginDtop.getPassword(), existingUser.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
        }

        String token = jwtUtil.generateToken(existingUser.getUserID(), existingUser.getEmail());


        return ResponseEntity.ok(new AuthResponse(token, existingUser.getUserID(), existingUser.getEmail()));
    }
}