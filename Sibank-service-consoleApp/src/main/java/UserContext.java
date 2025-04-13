



public class UserContext {
    private String userName;
    private String deviceID;
    private String publicKey;

    public UserContext(String userName, String deviceID, String publicKey) {
        this.userName = userName;
        this.deviceID = deviceID;
        this.publicKey = publicKey;
    }

    public String getUserName() {
        return userName;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
