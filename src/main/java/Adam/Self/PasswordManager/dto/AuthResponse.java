package Adam.Self.PasswordManager.dto;

import lombok.Getter;

@Getter
public class AuthResponse {
    private String token;
    private Long userId;
    private String email;

    public AuthResponse(String token, Long userId, String email) {
        this.token = token;
        this.userId = userId;
        this.email = email;
    }

}