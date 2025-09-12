package Adam.Self.PasswordManager.repository;

import Adam.Self.PasswordManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query method to find user by email
    User findByEmail(String email);
}