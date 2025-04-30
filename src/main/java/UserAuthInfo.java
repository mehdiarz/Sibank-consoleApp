import java.util.Map;

public class UserAuthInfo {
    private String deviceId;
    private String deviceModel;
    private String vPublicKey;

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getVPublicKey() {
        return vPublicKey;
    }

    public static UserAuthInfo fromMap(Map<String, Object> map) {
        UserAuthInfo info = new UserAuthInfo();
        info.deviceId = (String) map.getOrDefault("deviceId", "unknown-device-id");
        info.deviceModel = (String) map.getOrDefault("deviceModel", "unknown-model");
        info.vPublicKey = (String) map.getOrDefault("vPublicKey", "mock-public-key");
        return info;
    }
}
