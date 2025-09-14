package Adam.Self.PasswordManager.service;

import Adam.Self.PasswordManager.model.User;
import Adam.Self.PasswordManager.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String hashPassword(String rawPassword) {
        return org.springframework.security.crypto.bcrypt.BCrypt
                .hashpw(rawPassword, org.springframework.security.crypto.bcrypt.BCrypt.gensalt());
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

    public User patchUser(Long userId, Map<String, Object> updates){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));

        updates.forEach((key, value) -> {
            switch (key){
                case "name": user.setName((String) value); break;
                case "email": user.setEmail((String) value); break;
            }
        });

        return userRepository.save(user);
    }

    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

}