//public class RestConnection {
//    private static int loginWithPassAttempt = 0;
//    public static UserAuthInfo userAuthInfo;
//    public static UserInfo userInfo;
//    public static DeviceInfo deviceInfo = new DeviceInfo();
//    private static UserContext currentUserContext;
//
//    private static String userCertificate;
//
//    public static int getLoginWithPassAttempt() {
//        return loginWithPassAttempt;
//    }
//
//    public static void setLoginWithPassAttempt(int attempt) {
//        loginWithPassAttempt = attempt;
//    }
//
//    public static void incrementLoginWithPassAttempt() {
//        loginWithPassAttempt++;
//    }
//
//    public static boolean isWeb() {
//        return false; // Placeholder, simulate local/console usage
//    }
//
//    public static void setCurrentUserContext(UserContext context) {
//        currentUserContext = context;
//    }
//
//    public static UserContext getCurrentUserContext() {
//        return currentUserContext;
//    }
//
//    public static DeviceInfo getDeviceInfo() {
//        return deviceInfo;
//    }
//
//    public static void setUserAuthInfo(UserAuthInfo authInfo) {
//        userAuthInfo = authInfo;
//    }
//
//    public static void setUserInfo(UserInfo user) {
//        userInfo = user;
//    }
//
//    public static void setUserCertificate(String certificate) {
//        RestConnection.userCertificate = certificate;
//    }
//
//    public static String getUserCertificate() {
//        return userCertificate;
//    }
//}


import okhttp3.*;
import java.io.IOException;

public class RestConnection {
    private final OkHttpClient client;

    // Constructor to initialize OkHttpClient
    public RestConnection() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                .build();
    }

    // POST request with JSON body
    public String post(String url, String jsonBody) throws IOException {
        // Create RequestBody with JSON data
        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));

        // Build the request
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .post(body)
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string(); // Return the response body as a string
        }
    }
}

