package Adam.Self.PasswordManager.repository;

import Adam.Self.PasswordManager.model.User;
import Adam.Self.PasswordManager.model.VaultEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VaultRepository extends JpaRepository<User, Long> {
    // Custom query method to find user by email

}