
//
//
//
//public class UserContext {
//    private String userName;
//    private String deviceID;
//    private String publicKey;
//
//    public UserContext(String userName, String deviceID, String publicKey) {
//        this.userName = userName;
//        this.deviceID = deviceID;
//        this.publicKey = publicKey;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getDeviceID() {
//        return deviceID;
//    }
//
//    public String getPublicKey() {
//        return publicKey;
//    }
//}
//
//

import java.net.InetAddress;
import java.net.UnknownHostException;
public class UserContext {
    private String userName;
    private String deviceId;
    private String publicKey;
    private String localPass;
    private int authenticationMethod;
    private UserStatus status;
    private String deviceModel;
    private String mobileNumber;
    private String clientIp;



    public UserContext() {
        // default no-arg constructor
    }

    public UserContext(String userName, String deviceID, String publicKey) {
        this.userName = userName;
        this.deviceId = deviceID;
        this.publicKey = publicKey;
    }

    public UserContext(String userName, String deviceID, String deviceModel, String clientIp, String publicKey,
                       UserStatus status, int authenticationMethod, String localPass, String mobileNumber) {
        this.userName = userName;
        this.deviceId = deviceId;
        this.deviceModel = deviceModel;
        this.clientIp = clientIp;
        this.publicKey = publicKey;
        this.status = status;
        this.authenticationMethod = authenticationMethod;
        this.localPass = localPass;
        this.mobileNumber = mobileNumber;
    }

    public String toString() {
        return userName + "." + deviceId + "." + publicKey + "." + status + "." + mobileNumber + ".";
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) { this.userName = userName; }

    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceID(String deviceID) { this.deviceId = deviceID; }

    public String getPublicKey() {
        return publicKey;
    }
    public void setPublicKey(String publicKey) { this.publicKey = publicKey; }

    public void setLocalPass(String localPass) {
        this.localPass = localPass;
    }

    public String getLocalPass() {
        return localPass;
    }

    public void setAuthenticationMethod(int authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public int getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserStatus getStatus() {
        return status;
    }
    public void setMobileNumber(String mobileNumber) {
        this.deviceModel = mobileNumber;
    }

    public String getMobileNumber() {
        return deviceModel;
    }


    public void setClientIp(String clientIp) { this.clientIp = clientIp; }
    public String getClientIp() { return clientIp; }

    public static UserContext adminContext() throws UnknownHostException {
        UserContext context = new UserContext();
        context.userName = "xti22adm";
        context.publicKey = "123456";
        context.deviceId = DeviceInfo.getIdentifier();
        context.deviceModel = DeviceInfo.getDeviceModel();
        context.clientIp = InetAddress.getLocalHost().getHostAddress();
        return context;
    }
}