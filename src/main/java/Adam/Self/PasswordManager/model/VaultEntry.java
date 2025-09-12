package Adam.Self.PasswordManager.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Vaults")
public class VaultEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(unique = true, nullable = false)
    private String userId;
    private String encryptedData;
    private String serviceName;


    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}

    public String getEncryptedData() {return encryptedData;}
    public void setEncryptedData(String encryptedData) {this.encryptedData = encryptedData;}

    public String getServiceName() {return serviceName;}
    public void setServiceName(String serviceName) {this.serviceName = serviceName;}


}
