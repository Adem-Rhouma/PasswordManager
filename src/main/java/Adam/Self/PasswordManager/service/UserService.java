package Adam.Self.PasswordManager.service;

import Adam.Self.PasswordManager.model.User;
import Adam.Self.PasswordManager.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        try {
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("User already exists");
        }catch (Exception e){
            throw new RuntimeException("Failed to register user: " + e.getMessage());
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}