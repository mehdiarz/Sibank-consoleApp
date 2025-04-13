import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.*;

public class Main {

    static final String SERVER_IP = "80.75.3.46";
    static final String PROTOCOL = "http://";
    static final String PORT = ":8092";
    static final String WEB_SERVICE_PATH = "/hostRest/xti22";
    static final String BASE_URL = PROTOCOL + SERVER_IP + PORT + WEB_SERVICE_PATH;

    static final String CONNECTION_BRANCH = "BR";
    static final String SPECIFIC_KEY = "Sina_SpecificProcesses";

    static UserContext userContext = new UserContext("999", "N", "123456");

    public static void main(String[] args) {
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

                processes.sort(Comparator.comparing(p -> p.version));
                ProcessModel latest = processes.get(processes.size() - 1);

                String instanceId = ApiService.startProcess(latest.id);
                Map<String, Object> variables = ApiService.getProcessVariables(instanceId);

                // Do something with the variables
                System.out.println("Received Variables: " + variables);

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}



