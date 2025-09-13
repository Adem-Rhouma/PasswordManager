package Adam.Self.PasswordManager.service;


import Adam.Self.PasswordManager.model.VaultEntry;
import Adam.Self.PasswordManager.repository.VaultRepository;
import org.springframework.stereotype.Service;

@Service
public class VaultService {

    private final VaultRepository vaultRepository;

    public VaultService(VaultRepository vaultRepository) {
        this.vaultRepository = vaultRepository;
    }

    public Long count() {
        return vaultRepository.count();
    }

    public void save(VaultEntry vaultEntry) {
        vaultRepository.save(vaultEntry);
    }

}
