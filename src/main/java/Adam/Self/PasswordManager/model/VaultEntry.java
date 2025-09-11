package Adam.Self.PasswordManager.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vault_entries")
public class VaultEntry {

    @Id
    private String id;
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
