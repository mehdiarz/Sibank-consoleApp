import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.json.JSONObject; // use org.json or gson if you prefer

public class LoggerInterceptor {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static void intercept(String processId, String method,String status, String json ,String result) {
        String timestamp = LocalDateTime.now().format(formatter);

        if (status == "request") {
            // Log request
            JSONObject requestLog = new JSONObject();
            requestLog.put("timestamp", timestamp);
            requestLog.put("processId", processId);
            requestLog.put("method", method);
            requestLog.put("inputs", json);
            requestLog.put("status", "request");
            requestLog.put("message", "Request started");

            writeLogToFile(requestLog);
        } else {



                // Log response success
                JSONObject responseLog = new JSONObject();
                responseLog.put("timestamp", timestamp);
                responseLog.put("processId", processId);
                responseLog.put("method", method);
                responseLog.put("status", status);
                responseLog.put("response", result);

                writeLogToFile(responseLog);



        }
    }

    private static void writeLogToFile(JSONObject log) {
        try (FileWriter file = new FileWriter("log.json", true)) {
            file.write(log.toString() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
