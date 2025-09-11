package Adam.Self.PasswordManager.service;


import Adam.Self.PasswordManager.model.User;
import Adam.Self.PasswordManager.repository.UserRepository;
import Adam.Self.PasswordManager.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(String email, String password){
        if(userRepository.findByEmail(email).isPresent()){
            throw new RuntimeException("Email Already Exists");
        }
        User user = new User();
        
    }

}
