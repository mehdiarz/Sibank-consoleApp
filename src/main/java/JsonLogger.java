//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class JsonLogger {
//
//    private static final String LOG_DIR = "logs";
//
//    public static void logJson(String jsonContent) {
//        try {
//            // Ensure the logs directory exists
//            Path logDirPath = Paths.get(LOG_DIR);
//            if (!Files.exists(logDirPath)) {
//                Files.createDirectories(logDirPath);
//            }
//
//            // Generate a timestamped filename
//            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
//            String filename = "response_" + timestamp + ".json";
//            Path filePath = logDirPath.resolve(filename);
//
//            // Write the JSON content to the file
//            try (FileWriter fileWriter = new FileWriter(filePath.toFile())) {
//                fileWriter.write(jsonContent);
//                System.out.println("Logged JSON response to " + filePath.toAbsolutePath());
//            }
//        } catch (IOException e) {
//            System.err.println("Failed to write JSON log: " + e.getMessage());
//        }
//    }
//}

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonLogger {
    private static final String LOG_FILE_PATH = "logs/response_log.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void log(String processId, String method, String status, Object message) {
        Map<String, Object> logEntry = new LinkedHashMap<>();
        logEntry.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        logEntry.put("processId", processId);
        logEntry.put("method", method);
        logEntry.put("status", status);
        logEntry.put("message", message);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(mapper.writeValueAsString(logEntry));
            writer.newLine(); // instead of FileWriter.file.write(System.lineSeparator())
        } catch (IOException e) {
            System.err.println("Failed to write log entry: " + e.getMessage());
        }
    }

}