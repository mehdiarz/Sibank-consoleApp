import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.json.JSONObject; // use org.json or gson if you prefer

public class LoggerInterceptor1 {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static <T> T intercept1(String processId, String method, InterceptedCall<T> call) {
        String timestamp = LocalDateTime.now().format(formatter);

        // Log request
        JSONObject requestLog = new JSONObject();
        requestLog.put("timestamp", timestamp);
        requestLog.put("processId", processId);
        requestLog.put("method", method);
        requestLog.put("status", "request");
        requestLog.put("message", "Request started");

        writeLogToFile(requestLog);

        try {
            T result = call.execute();

            // Log response success
            JSONObject responseLog = new JSONObject();
            responseLog.put("timestamp", timestamp);
            responseLog.put("processId", processId);
            responseLog.put("method", method);
            responseLog.put("status", "success");
            responseLog.put("message", result);

            writeLogToFile(responseLog);

            return result;
        } catch (Exception e) {
            // Log response failure
            JSONObject errorLog = new JSONObject();
            errorLog.put("timestamp", timestamp);
            errorLog.put("processId", processId);
            errorLog.put("method", method);
            errorLog.put("status", "error");
            errorLog.put("message", e.getMessage());

            writeLogToFile(errorLog);

            throw new RuntimeException(e);
        }
    }

    private static void writeLogToFile(JSONObject log) {
        try (FileWriter file = new FileWriter("log.json", true)) {
            file.write(log.toString() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Functional interface to pass your API call
    @FunctionalInterface
    public interface InterceptedCall<T> {
        T execute() throws Exception;
    }
}
