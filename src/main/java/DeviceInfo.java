//public class DeviceInfo {
//    private String identifier = "DEVICE-1234";
//    private String deviceModel = "MockModel-X";
//    private String deviceName = "MockDevice";
//    private String clientIp = "192.168.0.100";
//
//    public String getIdentifier() {
//        return identifier;
//    }
//
//    public String getDeviceModel() {
//        return deviceModel;
//    }
//
//    public String getDeviceName() {
//        return deviceName;
//    }
//
//    public String getClientIp() {
//        return clientIp;
//    }
//}
import java.util.UUID;

class DeviceInfo {
    public static String getIdentifier() { return UUID.randomUUID().toString(); }
    public static String getDeviceModel() { return System.getProperty("os.name") + "-" + System.getProperty("os.version"); }
}