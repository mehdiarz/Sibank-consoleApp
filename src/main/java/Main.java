import java.io.IOException;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Main {

    // === Constants ===

    // Create an ObjectMapper instance

    static  String SERVER_IP ;
    static  String PROTOCOL ;
    static  String PORT ;
    static  String WEB_SERVICE_PATH ;
    static  String BASE_URL = PROTOCOL + SERVER_IP + PORT + WEB_SERVICE_PATH;

    static  String CONNECTION_BRANCH ;
    static  String SPECIFIC_KEY ;

    static  String mobileNumber ;
    static  String Secure_ChannelUserName ;
    static  String Secure_ChannelPassword ;
    static  String initiator ;
    static  String deviceId ;

    static  String deviceModel ;
    static  String clientIp ;

    // === User Context ===
    static UserContext userContext = new UserContext("xti22adm", "BR", "123456");

    public static void main(String[] args) {
        // Prevent HTTP 417 Expectation errors and optimize connection
        System.setProperty("sun.net.http.retryPost", "false");
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        System.setProperty("http.keepAlive", "true");
        System.setProperty("sun.net.http.disableKeepAlive", "true");
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        System.setProperty("sun.net.http.errorstream.enableBuffering", "true");
        System.setProperty("http.keepAlive", "false");
        System.setProperty("sun.net.http.retryPost", "false");

        try {
            loadConfig();
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            return; // Exit if config loading fails
        }

        // Timer for scheduled process check
        Timer timer = new Timer();
        timer.schedule(new APICaller(), 0, 60 * 1000); // every 1 minute
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LoginServiceScheduler.callLoginAPI();
            }
        }, 0, 60 * 1000);



    }

    public static void loadConfig() throws IOException {
        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Read the JSON file and map it to a Configuration object
        Config config = objectMapper.readValue(new File("Configuration/Configuration File.json"), Config.class);



        SERVER_IP = config.server_IP;
        PROTOCOL = config.protocol;
        PORT = config.port;
        WEB_SERVICE_PATH = config.web_SERVICE_PATH;
        BASE_URL = PROTOCOL + SERVER_IP + PORT + WEB_SERVICE_PATH;

        // Initialize other settings
        CONNECTION_BRANCH = config.connection_BRANCH;
        SPECIFIC_KEY = config.specific_KEY;

        // Initialize userContext
        userContext = config.userContext;
        // Initialize userContext from config

        mobileNumber = config.mobileNumber;
        Secure_ChannelUserName = config.secure_ChannelUserName;
        Secure_ChannelPassword = config.secure_ChannelPassword;
        initiator = config.initiator;
        deviceId = config.deviceId;

        // Initialize other settings
        deviceModel = config.deviceModel;
        clientIp = config.clientIp;

        System.out.println("UserContext initialized: " + userContext.getUserName());
    }

    static class APICaller extends TimerTask {
        @Override
        public void run() {
            try {
                System.out.println("Calling process...");

                List<ProcessModel> processes = ApiService.getProcesses(SPECIFIC_KEY);

                if (processes.isEmpty()) throw new Exception("No processes found");

                // Sort by version and pick the latest
                processes.sort(Comparator.comparing(p -> p.version));
                ProcessModel latest = processes.get(processes.size() - 1);

                // Start the process
                String instanceId = ApiService.startProcess(SPECIFIC_KEY,latest.id);
                System.out.println("Started process instance: " + instanceId);

                // Fetch and print variables
                Map<String, Object> variables = ApiService.getProcessVariables(instanceId);
                System.out.println("Received Variables: " + variables);

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace(); // Show full error trace
            }
        }
    }
}



