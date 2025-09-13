package Adam.Self.PasswordManager.service;

import Adam.Self.PasswordManager.model.User;
import Adam.Self.PasswordManager.model.VaultEntry;
import Adam.Self.PasswordManager.repository.UserRepository;
import Adam.Self.PasswordManager.repository.VaultRepository;
import org.aspectj.weaver.ast.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {


    private final UserService userService;
    private final VaultService vaultService;

    public DataSeeder(VaultService vaultService, UserService userService) {
        this.userService = userService;
        this.vaultService = vaultService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only seed if no users exist
        if (vaultService.count() == 0) {
            // Create default user
            User defaultUser = new User();
            defaultUser.setName("Admin");
            defaultUser.setEmail("admin@example.com");
            defaultUser.setPasswordHash("admin123"); // TODO: hash before production
            System.out.println(defaultUser.getPasswordHash());
            System.out.println("HALLLOOOOOOOOOOOOO?////////////////////////////////////////////////////////////////");
            userService.registerUser(defaultUser);


            User TestUser = new User();
            TestUser.setName("TestUser");
            TestUser.setEmail("admin@example.com");
            TestUser.setPasswordHash("admin1234567");
            userService.registerUser(defaultUser);
//            System.out.println(Brian.getPasswordHash());
//            userRepository.save(defaultUser);

            // Create a couple of vault entries for this user
            VaultEntry entry1 = new VaultEntry();
            entry1.setVaultName("Personal Gmail");
            entry1.setUser(defaultUser);
            entry1.setWebsite("https://mail.google.com");
            entry1.setUsername("admin@gmail.com");
            entry1.setEmail("admin@gmail.com");
            entry1.setPasswordEncrypted("encryptedPassword1"); // TODO: encrypt properly
            vaultService.save(entry1);

            VaultEntry entry2 = new VaultEntry();
            entry2.setVaultName("Facebook");
            entry2.setUser(defaultUser);
            entry2.setWebsite("https://facebook.com");
            entry2.setUsername("admin_fb");
            entry2.setEmail("admin_fb@gmail.com");
            entry2.setPasswordEncrypted("encryptedPassword2");
            vaultService.save(entry2);

            System.out.println("Seed data created: default user + 2 vault entries");
        }
    }
}
