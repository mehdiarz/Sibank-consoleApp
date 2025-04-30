public class ActivationInfo {
    private UserInfo userInfo;
    private int authenticationMethod;

    // Constructor that accepts a UserInfo object
    public ActivationInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    // Getter and setter for userInfo
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(int authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }
}