package Adam.Self.PasswordManager.controller;


import Adam.Self.PasswordManager.dto.VaultRegistryDtop;
import Adam.Self.PasswordManager.model.VaultEntry;
import Adam.Self.PasswordManager.repository.VaultRepository;
import Adam.Self.PasswordManager.security.JwtUtil;
import Adam.Self.PasswordManager.service.VaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vault")
public class VaultController {


    private final VaultService vaultService;
    private final JwtUtil jwtUtil;

    public VaultController(VaultService vaultService, JwtUtil jwtUtil) {
        this.vaultService = vaultService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<List<VaultEntry>> getVaultEntires(@RequestHeader("Authorization") String authHeader){
        Long userId = jwtUtil.getUserId(authHeader);
        return ResponseEntity.ok(vaultService.getVaultEntries(userId));
    }

    @GetMapping("/{vaultId}")
    public ResponseEntity<VaultEntry> getVaultEntry(@RequestHeader("Authorization") String authHeader,
                                                    @PathVariable Long vaultId){
        Long userId = jwtUtil.getUserId(authHeader);
        return ResponseEntity.ok(vaultService.getVaultEntry(userId, vaultId));
    }

    @PostMapping
    public ResponseEntity<VaultEntry> addVaultEntry(@RequestHeader("Authorization") String authHeader, @RequestBody VaultRegistryDtop entry){
        Long userId = jwtUtil.getUserId(authHeader);
        VaultEntry vaultEntry = new VaultEntry();

        vaultEntry.setVaultName(entry.getVaultName());
        vaultEntry.setWebsite(entry.getWebsite());
        vaultEntry.setEmail(entry.getEmail());
        vaultEntry.setUsername(entry.getUsername());
        vaultEntry.setPasswordEncrypted(entry.getPasswordEncrypted());


        return ResponseEntity.ok(vaultService.addVaultEntry(userId, vaultEntry));
    }

    @PatchMapping("/{vaultId}")
    public ResponseEntity<VaultEntry> patchVaultEntry(@RequestHeader("Authorization") String authHeader,
                                                      @PathVariable Long vaultId,
                                                      @RequestBody Map<String, Object> updates){
        Long userId = jwtUtil.getUserId(authHeader);
        return ResponseEntity.ok(vaultService.patchVaultEntry(userId, vaultId, updates));
    }

    @DeleteMapping("/{vaultId}")
    public ResponseEntity<String> deleteVaultEntry(@RequestHeader("Authorization") String authHeader,
                                                   @PathVariable Long vaultId){
        Long userId = jwtUtil.getUserId(authHeader);
        vaultService.deleteVaultEntry(userId, vaultId);
        return ResponseEntity.ok("Vault entry deleted successfully");
    }


}
