package Adam.Self.PasswordManager.service;


import Adam.Self.PasswordManager.dto.VaultRegistryDtop;
import Adam.Self.PasswordManager.model.User;
import Adam.Self.PasswordManager.model.VaultEntry;
import Adam.Self.PasswordManager.repository.VaultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class VaultService {

    private final VaultRepository vaultRepository;

    private final UserService userService;

    public VaultService(VaultRepository vaultRepository, UserService userService) {
        this.vaultRepository = vaultRepository;
        this.userService = userService;
    }

    public Long count() {
        return vaultRepository.count();
    }

    public void save(VaultEntry vaultEntry) {
        vaultRepository.save(vaultEntry);
    }

    public List<VaultEntry> getVaultEntries(Long userId){
        return vaultRepository.findByUser_Id(userId);
    }

    public VaultEntry getVaultEntry(Long userId, Long vaultId){
        VaultEntry vaultEntry = vaultRepository.findById(vaultId).orElseThrow(() -> new RuntimeException("Vault entry not found"));
        if (!vaultEntry.getUser().getId().equals(userId)) {
            throw new RuntimeException("User is not the same user ID");
        }
        return vaultEntry;
    }

    public VaultEntry addVaultEntry(Long userId, VaultEntry entry){



        User user = userService.findById(userId);
        entry.setUser(user);
        return vaultRepository.save(entry);
    }

    public VaultEntry patchVaultEntry(Long userId, Long vaultId, Map<String, Object> updates){
        VaultEntry entry = getVaultEntry(userId,  vaultId);
        updates.forEach((key, value) -> {
            switch (key){
                case "vaultName": entry.setVaultName((String) value); break;
                case "website": entry.setWebsite((String) value); break;
                case "email": entry.setEmail((String) value); break;
            }
        });
        return vaultRepository.save(entry);
    }


    public void deleteVaultEntry(Long userId, Long vaultId){
        VaultEntry entry =  getVaultEntry(userId,  vaultId);
        vaultRepository.delete(entry);
    }



}
