import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.net.ssl.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

public class LoginServiceScheduler {


//    static  String mobileNumber ;
//    static  String Secure_ChannelUserName ;
//    static  String Secure_ChannelPassword ;
//    static  String initiator ;
//    static  String deviceId ;
//
//    static  String deviceModel ;
//    static  String clientIp ;

    private static final OkHttpClient httpClient = createSecureHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

//    public static void main(String[] args) {
////        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
////        scheduler.scheduleAtFixedRate(LoginServiceScheduler::callLoginAPI, 0, 1, TimeUnit.MINUTES);
//
//        try {
//            loadConfig();
//        } catch (IOException e) {
//            System.err.println("Error loading configuration: " + e.getMessage());
//            return; // Exit if config loading fails
//        }
//
//
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                LoginServiceScheduler.callLoginAPI();
//            }
//        }, 0, 60 * 1000);
//    }

    public static List<ProcessModel>  getProcesses(String key) throws IOException {
        String url = Main.BASE_URL + "/prcs-by-key?start=0&size=400&order=desc&key=" + key;
        HttpURLConnection conn = createConnection(url, "GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonResponse = response.toString();
            // Log the JSON response
//            JsonLogger.logJson(jsonResponse);

            ObjectMapper mapper = new ObjectMapper();
            ProcessListResponse result = mapper.readValue(jsonResponse, ProcessListResponse.class);
            return result.data;

        } else {
            throw new RuntimeException("HTTP GET Request Failed with Error code : " + responseCode);
        }

    }

    private static HttpURLConnection createConnection(String urlStr, String method) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");
//        conn.setRequestProperty("Expect", "");
        conn.setRequestProperty("token", Main.userContext.getPublicKey());
        conn.setRequestProperty("user", Main.userContext.getUserName());
        conn.setRequestProperty("branch", Main.CONNECTION_BRANCH);
        if (method.equals("POST")) {
            conn.setDoOutput(true);
        }
        return conn;
    }

    public static void callLoginAPI() {


        try {

//            List<ProcessModel> processes = ApiService.getProcesses("Process_MPSina_SendLoginInfo");
//            UserContext userContext = UserContext.adminContext();
//            processes.sort(Comparator.comparing(p -> p.version));
//            ProcessModel latest = processes.get(processes.size() - 1);

//             Start the process
//            String instanceId = ApiService.startProcess("Process_MPSina_SendLoginInfo",latest.id);
//            System.out.println("Started process instance: " + instanceId);
//
//            // Fetch and print variables
//            Map<String, Object> variables = ApiService.getProcessVariables(instanceId);
//            System.out.println("Received Variables: " + variables);





//            String businessKey = Main.userContext.getUserName() + ":" + latest.id + ":" + new Date().toString();
//            String encodedKey = URLEncoder.encode(businessKey, "UTF-8");
//            String url = Main.BASE_URL + "/prcs/" + latest.id + "/start?businessKey=" + encodedKey;
//
//
//            HttpURLConnection conn = createConnection(url, "POST");


//            return new ObjectMapper().readValue(responseText, StartProcessResponseBean.class);



            Map<String, String> securityParams = new HashMap<>();
            securityParams.put("mobileNumber", Main.mobileNumber);
            securityParams.put("Secure_ChannelUserName", Main.Secure_ChannelUserName);
            securityParams.put("Secure_ChannelPassword", Main.Secure_ChannelPassword);
            securityParams.put("initiator", Main.initiator);
            securityParams.put("deviceId", Main.deviceId);
            securityParams.put("deviceModel", Main.deviceModel);
            securityParams.put("clientIp", Main.clientIp);
            securityParams.put("time", String.valueOf(System.currentTimeMillis()));

            SecurityContextHolder.initUserCertificate(securityParams);

            // Simulate saving encrypted certificate
            boolean saved = SecurityContextHolder.saveUserCertificate(Main.Secure_ChannelUserName);
            System.out.println("Saved: " + saved);

            // Retrieve it later
            String cert = SecurityContextHolder.getUserCertificate(Main.Secure_ChannelUserName);
            System.out.println("Retrieved certificate: " + cert);

            System.out.println("decrtipt : " + test.decrypt(cert));
            List<String> empty = new ArrayList<String>();

            Map<String, Object> input = new HashMap<>();


            input.putIfAbsent("user", "xti22adm");
            input.putIfAbsent("UserCertificate", cert);
            input.putIfAbsent("time", String.valueOf(System.currentTimeMillis())); // Similar to DateTime.now().toString()
            input.putIfAbsent("deviceName", "SM-A518B");
            input.putIfAbsent("deviceId", "UP1A.231005.017");
            input.putIfAbsent("deviceModel", "samsung Android 13");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInput = "";
            jsonInput = objectMapper.writeValueAsString(input);

            System.out.println("startprocesswithreturn: " + ApiService.startProcessWithReturn("Process_MPSina_SendLoginStaticInfo" , empty, jsonInput, true));

            //

//            RequestBody body = RequestBody.create(cert, MediaType.parse("application/json"));
//            Request request = new Request.Builder()
//                    .url(url)
//                    .post(body)
//                    .addHeader("token", "28377382kKk@11")
//                    .addHeader("user", "m_ehdi")
//                    .addHeader("branch", "BR")
//                    .build();

//            Response response = httpClient.newCall(request).execute();
//            System.out.println("Response: " + response.body().string());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private static void loadConfig() throws IOException {
//        // Create an ObjectMapper instance
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // Read the JSON file and map it to a Configuration object
//        Config config = objectMapper.readValue(new File("Configuration/Configuration File.json"), Config.class);
//
//
//
//        mobileNumber = config.mobileNumber;
//        Secure_ChannelUserName = config.secure_ChannelUserName;
//        Secure_ChannelPassword = config.secure_ChannelPassword;
//        initiator = config.initiator;
//        deviceId = config.deviceId;
//
//        // Initialize other settings
//        deviceModel = config.deviceModel;
//        clientIp = config.clientIp;
//
//    }

    private static OkHttpClient createSecureHttpClient() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }

                        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}