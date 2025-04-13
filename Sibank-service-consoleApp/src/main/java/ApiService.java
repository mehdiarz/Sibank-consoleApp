

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.net.URLEncoder;

public class ApiService {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<ProcessModel> getProcesses(String key) throws IOException {
        String url = Main.BASE_URL + "/prcs-by-key?start=0&size=400&order=desc&key=" + key;
        HttpURLConnection conn = createConnection(url, "GET");
        Map<String, Object> response = mapper.readValue(conn.getInputStream(), new TypeReference<Map<String, Object>>() {});
        List<Map<String, Object>> processesData = (List<Map<String, Object>>) response.get("data");

        List<ProcessModel> processes = new ArrayList<>();
        for (Map<String, Object> proc : processesData) {
            String id = (String) proc.get("id");
            String version = String.valueOf(proc.get("version"));
            processes.add(new ProcessModel(id, version));
        }

        return processes;
    }

    public static String startProcess(String processId) throws IOException {
        String businessKey = Main.userContext.getUserName() + ":" + processId + ":" + new Date().toString();
//        String url = Main.BASE_URL + "/prcs/" + processId + "/start?businessKey=" + businessKey;
        String encodedKey = URLEncoder.encode(businessKey, "UTF-8");
        String url = Main.BASE_URL + "/prcs/" + processId + "/start?businessKey=" + encodedKey;

        HttpURLConnection conn = createConnection(url, "POST");
        String payload = "{}";
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = payload.getBytes();
            os.write(input, 0, input.length);
        }

        Map<String, Object> response = mapper.readValue(conn.getInputStream(), new TypeReference<Map<String, Object>>() {});
        return (String) response.get("processInstanceId");
    }

    public static Map<String, Object> getProcessVariables(String instanceId) throws IOException {
        String url = Main.BASE_URL + "/prcs-variables/" + instanceId;
        HttpURLConnection conn = createConnection(url, "GET");
        return mapper.readValue(conn.getInputStream(), new TypeReference<Map<String, Object>>() {});
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
}
