//import java.util.HashMap;
//import java.util.Map;
//
//public class SecurityContextHolder {
//    private static String localPass;
//    private static ActivationInfo currentUserStatus;
//    private static UserContext context;
//
//    public static UserContext getContext() {
//        return context;
//    }
//
//    public static void setContext(UserContext ctx) {
//        context = ctx;
//    }
//    public static void setLocalPass(String pass) {
//        localPass = pass;
//    }
//
//    public static String getLocalPass() {
//        return localPass;
//    }
//
//    public static void setCurrentUserStatus(ActivationInfo activationInfo) {
//        currentUserStatus = activationInfo;
//    }
//
//    public static ActivationInfo getCurrentUserStatus() {
//        return currentUserStatus;
//    }
//
//    public static void initUserCertificate(Map<String, String> params) {
//        // For test simulation
//        String cert = "CERT_" + params.getOrDefault("mobileNumber", "unknown") + "_" + System.currentTimeMillis();
//        RestConnection.setUserCertificate(cert);
//        System.out.println("User certificate initialized: " + cert);
//    }
//
//    public static void saveUserCertificate() {
//        // Simulate saving user certificate
//        System.out.println("User certificate saved: " + RestConnection.getUserCertificate());
//    }
//
//    public static void authenticateWithLocalPass(String inputPass) throws Exception {
//        if (localPass == null || !localPass.equals(inputPass)) {
//            throw new Exception("Local password authentication failed.");
//        }
//        System.out.println("Authenticated with local pass.");
//    }
//}


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class SecurityContextHolder {
    private static final String kq = "5lGEiZjwDUt8U360";
    private static final String iq = "SraDq2OSfDJ5A38k";
    public static String userCertificate = "";

    public static boolean saveUserCertificate(String username) {
        try {
            String initCertificate = SecureStorage.read("initUserCertificate");
            if (initCertificate == null) return false;

            SecureStorage.delete("initUserCertificate");

            userCertificate = EncryptHelper.encrypt(initCertificate, kq, iq);
            SecureStorage.write(username + "UserCertificate", userCertificate);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUserCertificate(String username) {
        try {
            userCertificate = SecureStorage.read(username + "UserCertificate");
            if (userCertificate == null) userCertificate = "";
        } catch (Exception e) {
            userCertificate = "";
        }
        return userCertificate;
    }

    public static void initUserCertificate(Map<String, String> securityParams) throws JsonProcessingException {
        // This would usually create the `initUserCertificate` and save it to secure storage.
        // Simulating with dummy value for test:
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(securityParams);
        SecureStorage.write("initUserCertificate", json);
    }
}
