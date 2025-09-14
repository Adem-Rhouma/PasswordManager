package Adam.Self.PasswordManager.controller;


import Adam.Self.PasswordManager.model.User;
import Adam.Self.PasswordManager.security.JwtUtil;
import Adam.Self.PasswordManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;


    public UserController(UserService userService,  JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getProfile(@RequestHeader("Authorization") String authHeader){
        System.out.println("Auth Header: " + authHeader);
        Long userId = jwtUtil.getUserId(authHeader);
        User user = userService.findById(userId);

        if(user != null){
            user.setPasswordHash(null);
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PatchMapping("/me")
    public ResponseEntity<User> updateProfile(@RequestHeader("Authorization") String authHeader,
                                              @RequestBody Map<String, Object> updates){

        Long userId = jwtUtil.getUserId(authHeader);
        User updatedUser = userService.patchUser(userId, updates);

        updatedUser.setPasswordHash(null);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/me")
    public ResponseEntity<String> deleteProfile(@RequestHeader("Authorization") String authHeader){
        Long userId = jwtUtil.getUserId(authHeader);
        userService.deleteUser(userId);
        return ResponseEntity.ok("User account deleted successfully");
    }

}
