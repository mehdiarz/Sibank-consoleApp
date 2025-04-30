public class UserInfo {
    private String userName;
    private String deviceId;
    private String deviceModel;
    private String publicKey;
    private String clientIp;
    private UserInServerStatus status;
    private String mobileNumber;
    public UserInfo(String userName, String deviceId, String deviceModel,
                    String publicKey, String clientIp,
                    UserInServerStatus status, String mobileNumber) {
        this.userName = userName;
        this.deviceId = deviceId;
        this.deviceModel = deviceModel;
        this.publicKey = publicKey;
        this.clientIp = clientIp;
        this.status = status;
        this.mobileNumber = mobileNumber;
    }
    public UserContext getUserContext() {
        return new UserContext(userName, deviceId, deviceModel, clientIp, publicKey,
                UserStatus.RegisteredInDeviceActiveInServer, 1, "1234", mobileNumber);
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public UserInServerStatus getStatus() {
        return status;
    }

    public void setStatus(UserInServerStatus status) {
        this.status = status;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
//    public UserContext getUserContext() {
//        UserContext ctx = new UserContext();
//        ctx.setUserName(this.userName);
//        ctx.setDeviceID(this.deviceId);
//        ctx.setPublicKey(this.publicKey);
//        ctx.setClientIp(this.clientIp);
//        ctx.setStatus(UserStatus.Unknown); // default status, will be updated later
//        ctx.setMobileNumber(this.mobileNumber);
//        return ctx;
//    }

}