package Adam.Self.PasswordManager.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaultRegistryDtop {

    @NotBlank
    @Size(max = 100)
    private String vaultName;

    private String website;
    private String username;
    private String email;

    @NotBlank
    private String passwordEncrypted;


}
