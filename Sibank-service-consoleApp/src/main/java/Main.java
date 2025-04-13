import java.io.IOException;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    // === Constants ===
    static final String SERVER_IP = "10.35.31.19";
    static final String PROTOCOL = "http://";
    static final String PORT = ":8092";
    static final String WEB_SERVICE_PATH = "/hostRest/xti22";
    static final String BASE_URL = PROTOCOL + SERVER_IP + PORT + WEB_SERVICE_PATH;

    static final String CONNECTION_BRANCH = "BR";
    static final String SPECIFIC_KEY = "Sina_SpecificProcesses";

    // === User Context ===
    static UserContext userContext = new UserContext("xti22adm", "BR", "123456");

    public static void main(String[] args) {
        // Prevent HTTP 417 Expectation errors and optimize connection
        System.setProperty("sun.net.http.retryPost", "false");
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        System.setProperty("http.keepAlive", "true");
        System.setProperty("sun.net.http.disableKeepAlive", "true");

        // Timer for scheduled process check
        Timer timer = new Timer();
        timer.schedule(new APICaller(), 0, 60 * 1000); // every 1 minute
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
                String instanceId = ApiService.startProcess(latest.id);
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
