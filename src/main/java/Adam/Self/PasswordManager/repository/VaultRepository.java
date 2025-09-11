package Adam.Self.PasswordManager.repository;

import Adam.Self.PasswordManager.model.VaultEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VaultRepository extends MongoRepository<VaultEntry, String> {
    List<VaultEntry> findByUserId(String userId);
}
