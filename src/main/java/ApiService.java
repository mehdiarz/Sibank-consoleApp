

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONObject;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.Map;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.net.URLEncoder;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.io.*;

public class ApiService {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<ProcessModel>  getProcesses(String key) throws IOException {
        try {
            Main.loadConfig();
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
        String url = Main.BASE_URL + "/prcs-by-key?start=0&size=400&order=desc&key=" + key;

        Map<String, Object> input = new HashMap<>();

        input.putIfAbsent("url", url);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = "";
        jsonInput = objectMapper.writeValueAsString(input);
        input.clear();
//        HttpURLConnection conn = createConnection(url, "GET");
//        LoggerInterceptor.intercept( key, "GET", "request" ,jsonInput, " ");

        HttpURLConnection conn;
        {
            conn = createConnection(url, "GET");
            LoggerInterceptor.intercept(key, "GET", "request", jsonInput, " ");
        }
        int responseCode = conn.getResponseCode();

        String status = (responseCode >= 200 && responseCode < 300) ? "success" : "error";



        String responseString;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            responseString = in.lines().collect(Collectors.joining("\n"));
        }

        // Log the response string
        LoggerInterceptor.intercept(key, "GET", status, " ", responseString);

//        Map<String, Object> responseMap = objectMapper.readValue(conn.getInputStream(), new TypeReference<Map<String, Object>>() {});
//
//        // Convert Map to JSONObject
//        JSONObject jsonResponse1 = new JSONObject(responseMap);
//
//        // Convert JSONObject to JSON string
//        String jsonString = jsonResponse1.toString();
//
//        LoggerInterceptor.intercept( key, "GET", status ," ", jsonString);
//        LoggerInterceptor1.intercept1(key, "GET", () -> {
//            HttpURLConnection conn1 = createConnection(url, "GET");
//            return conn1 ; // This should return JSON/String/Object to log
//        });




        if (responseCode == HttpURLConnection.HTTP_OK) {
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            String jsonResponse = response.toString();
//            // Log the JSON response
////            JsonLogger.logJson(jsonResponse);
//
//            ObjectMapper mapper = new ObjectMapper();
            ProcessListResponse result = objectMapper.readValue(responseString, ProcessListResponse.class);

//            ProcessListResponse result = mapper.readValue(jsonResponse, ProcessListResponse.class);

//            String status = (responseCode >= 200 && responseCode < 300) ? "success" : "error";
//
//            String jsonString = objectMapper.writeValueAsString(result.data);
//
//            LoggerInterceptor.intercept( key, "GET", status ," ", jsonString);

//            JsonLogger.log( key, "GET", status, result.data);
            return result.data;

        } else {


            String errorMessage = "";
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                String line;
                StringBuilder errorResponse = new StringBuilder();
                while ((line = errorReader.readLine()) != null) {
                    errorResponse.append(line);
                }
                errorMessage = errorResponse.toString();
            }

//            LoggerInterceptor.intercept( key, "GET", status ," ", errorMessage);

//            JsonLogger.log(key, "GET", status, responseCode);
            throw new RuntimeException("HTTP GET Request Failed with Error code : " + responseCode);
        }

//        Map<String, Object> response = mapper.readValue(conn.getInputStream(), new TypeReference<Map<String, Object>>() {});
//        List<Map<String, Object>> processesData = (List<Map<String, Object>>) response.get("data");
//
//        List<ProcessModel> processes = new ArrayList<>();
//        for (Map<String, Object> proc : processesData) {
//            String id = (String) proc.get("id");
//            String version = String.valueOf(proc.get("version"));
//            processes.add(new ProcessModel(id, version));
//        }
//
//        return processes;
    }



    public static String startProcess(String key, String processId) throws IOException {
//        String businessKey = Main.userContext.getUserName() + ":" + processId + ":" + new Date().toString();
//        String encodedKey = URLEncoder.encode(businessKey, "UTF-8");
//        String url = Main.BASE_URL + "/prcs/" + processId + "/start?businessKey=" + encodedKey;
//
//        HttpURLConnection conn = createConnection(url, "POST");
//        String payload = "{}";
//
//        try (OutputStream os = conn.getOutputStream()) {
//            byte[] input = payload.getBytes();
//            os.write(input, 0, input.length);
//        }
//
//        int responseCode = conn.getResponseCode();
//        InputStream inputStream;
//
//        if (responseCode >= 200 && responseCode < 300) {
//            inputStream = conn.getInputStream();
//        } else {
//            inputStream = conn.getErrorStream(); // Read real backend error message
//        }
//
//        Map<String, Object> response;
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//            String jsonResponse = reader.lines().collect(Collectors.joining("\n"));
//            String status = (responseCode >= 200 && responseCode < 300) ? "success" : "error";
////            JsonLogger.logJson(jsonResponse);
//            Object jsonObject;
//            try {
//                jsonObject = new ObjectMapper().readValue(jsonResponse, Object.class);
//            } catch (Exception ex) {
//                jsonObject = jsonResponse; // fallback if JSON parsing fails
//            }
//            JsonLogger.log(processId, status, jsonObject);
//            System.out.println("Server response (" + responseCode + "): " + jsonResponse);
//
//            if (responseCode >= 200 && responseCode < 300) {
//                response = mapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {});
//            } else {
//                throw new IOException("Server returned error:\n" + jsonResponse);
//            }
//        }
//
//        // Optional: Call prcs-by-key again (if still needed)
//        String url1 = Main.BASE_URL + "/prcs-by-key?start=0&size=400&order=desc&key=" + key;
//        HttpURLConnection connection = null;
//
//        try {
//            URL url2 = new URL(url1);
//            connection = (HttpURLConnection) url2.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setDoOutput(true);
//            connection.getOutputStream().close();
//
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//                String prcsByKeyResponse = reader.lines().collect(Collectors.joining("\n"));
//                System.out.println("prcs-by-key response:\n" + prcsByKeyResponse);
//            }
//
//        } catch (IOException e) {
//            if (connection != null && connection.getErrorStream() != null) {
//                try (BufferedReader errorReader = new BufferedReader(
//                        new InputStreamReader(connection.getErrorStream()))) {
//                    String errorBody = errorReader.lines().collect(Collectors.joining("\n"));
//                    System.err.println("Error in prcs-by-key:\n" + errorBody);
//                }
//            }
//        }
//
//        return (String) response.get("processInstanceId");
        String businessKey = Main.userContext.getUserName() + ":" + processId + ":" + new Date().toString();
        String encodedKey = URLEncoder.encode(businessKey, "UTF-8");
        String url = Main.BASE_URL + "/prcs/" + processId + "/start?businessKey=" + encodedKey;

        HttpURLConnection conn = createConnection(url, "POST");
        Map<String, Object> input1 = new HashMap<>();

        input1.putIfAbsent("url", url);
        input1.putIfAbsent("token", UserContext.adminContext().getPublicKey());
        input1.putIfAbsent("user", UserContext.adminContext().getUserName());
        input1.putIfAbsent("branch", "BR");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = "";
        jsonInput = objectMapper.writeValueAsString(input1);
        input1.clear();
        LoggerInterceptor.intercept( processId, "POST", "request" ,jsonInput, " ");

        String payload = "{}";

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = payload.getBytes();
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        InputStream inputStream = (responseCode >= 200 && responseCode < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        String jsonResponse;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            jsonResponse = reader.lines().collect(Collectors.joining("\n"));
        }

        Object jsonObject;
        try {
            jsonObject = new ObjectMapper().readValue(jsonResponse, Object.class);
        } catch (Exception ex) {
            jsonObject = jsonResponse; // fallback if JSON parsing fails
        }

        String status = (responseCode >= 200 && responseCode < 300) ? "success" : "error";

        jsonInput = objectMapper.writeValueAsString(jsonObject);

        LoggerInterceptor.intercept( processId, "POST", status ," ", jsonInput);

//        JsonLogger.log(processId, "POST", status, jsonObject); // pass parsed object (Map or String)

        if (status.equals("success") && jsonObject instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) jsonObject;
            return (String) map.get("processInstanceId");
        } else {
            throw new IOException("Server returned error:\n" + jsonResponse);
        }
    }

    public static Map<String, Object> getProcessVariables(String instanceId) throws IOException {
        String url = Main.BASE_URL + "/prcs-variables/" + instanceId;
        HttpURLConnection conn = createConnection(url, "GET");
        Map<String, Object> input1 = new HashMap<>();

        input1.putIfAbsent("url", url);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = "";
        jsonInput = objectMapper.writeValueAsString(input1);
        input1.clear();
        LoggerInterceptor.intercept( instanceId, "GET", "request" ,jsonInput, " ");
        int responseCode = conn.getResponseCode();
        String status = (responseCode >= 200 && responseCode < 300) ? "success" : "error";
        Map<String, Object> responseMap = objectMapper.readValue(conn.getInputStream(), new TypeReference<Map<String, Object>>() {});

        // Convert Map to JSONObject
        JSONObject jsonResponse = new JSONObject(responseMap);

        // Convert JSONObject to JSON string
        String jsonString = jsonResponse.toString();
        LoggerInterceptor.intercept( instanceId, "GET", status ," ", jsonString);
        return responseMap;
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


    private static Set<String> cachedServiceStart = ConcurrentHashMap.newKeySet();
    private static Map<String, Map<String, Object>> cachedResults = new ConcurrentHashMap<>();

    public static Map<String, Object> startProcessWithReturn(String prcId, List<String> selectedVarsResult, String processVars, boolean byAdmin) {
        Map<String, Object> vars = new HashMap<>();
        String deploymentID = "";
        String instanceID = "";

        if (cachedServiceStart.contains(prcId)) {
            if (cachedResults.containsKey(prcId)) {
                System.out.println("Returning cached result for: " + prcId);
                return cachedResults.get(prcId);
            }
        }

        try {

            List<ProcessModel> processes = getProcesses(prcId);

            if (!processes.isEmpty()) {



                processes.sort(Comparator.comparing(p -> p.version));
                ProcessModel latest = processes.get(processes.size() - 1);
//                int latestVersion = processes.get(0).id();
                deploymentID = latest.id;

//                JsonLogger.log("GET Process: "+ prcId , "success", latest);

//                for (Process process : processes.getProcesses()) {
//                    if (process.getVersion() > latestVersion) {
//                        latestVersion = process.getVersion();
//                        deploymentID = process.getId();
//                    }
//                }

                try {
                    Map<String, Object> result = startProcessWithResult(deploymentID, processVars, byAdmin = true);

                    if (result != null) {
                        instanceID = (String) result.get("processInstanceId");

                        Map<String, Object> variables = (Map<String, Object>) result.get("variables");
                        Object details = variables.get("details");

                        if (details instanceof List) {
                            List<Map<String, Object>> processVar = (List<Map<String, Object>>) details;
                            if (selectedVarsResult.isEmpty()) {
                                for (Map<String, Object> element : processVar) {
                                    vars.put((String) element.get("name"), element.get("value"));
                                }
                            } else {
                                for (Map<String, Object> element : processVar) {
                                    String name = (String) element.get("name");
                                    if (selectedVarsResult.contains(name)) {
                                        vars.put(name, element.get("value"));
                                    }
                                }
                            }
                        }
                    }

                } catch (Exception e) {
//                    JsonLogger.log(deploymentID, "error", e.getMessage());
                    System.out.println("Error in startProcessWithResult: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error getting processes: " + e.getMessage());
        }

        if (cachedServiceStart.contains(prcId)) {
            cachedResults.putIfAbsent(prcId, vars);
            System.out.println("Added to cache: " + prcId);
        }

        return vars;
    }

    public static Map<String, Object> startProcessWithResult(String processId, String variables, boolean byAdmin) throws Exception {
        // In Dart, if (kIsWeb) { ... } means special logic for Web, which we skip for now
        // If you need web detection, you can add manually later.


//        String businessKey = getBusinessKey(processId);
        String businessKey = Main.userContext.getUserName() + ":" + processId + ":" + new Date().toString();
        String encodedKey = URLEncoder.encode(businessKey, "UTF-8");
        String url = Main.BASE_URL +"/prcs/" + processId + "/startWithReturn?businessKey=" + encodedKey;



        Map<String, Object> input1 = new HashMap<>();

        input1.putIfAbsent("url", url);
        input1.putIfAbsent("token", UserContext.adminContext().getPublicKey());
        input1.putIfAbsent("user", UserContext.adminContext().getUserName());
        input1.putIfAbsent("branch", "BR");
        input1.putIfAbsent("variables", variables);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = "";
        jsonInput = objectMapper.writeValueAsString(input1);
        input1.clear();
        LoggerInterceptor.intercept( processId, "POST", "request" ,jsonInput, " ");
        Response response = postWithResponse(url, variables, byAdmin = true);



        String status = (response.getStatusCode() >= 200 && response.getStatusCode() < 300) ? "success" : "error";

        Map<String, Object> responseMap = objectMapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});

        // Convert Map to JSONObject
        JSONObject jsonResponse = new JSONObject(responseMap);

        // Convert JSONObject to JSON string
        String jsonString = jsonResponse.toString();

        LoggerInterceptor.intercept( processId, "POST", status ," ", jsonString);
//        JsonLogger.log("Process_MPSina_SendLoginStaticInfo: " + processId, "POST", status, mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {})); // pass parsed object (Map or String)

        if (response != null && (response.getStatusCode() == 200 || response.getStatusCode() == 201)) {
            try {
                return mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
            } catch (Exception ex) {
                System.out.println("Failed to parse successful JSON response.");
                ex.printStackTrace();
                return new HashMap<>(); // Return empty result safely
            }
        } else {
            System.out.println("Raw response body: " + response.getBody());
            Map<String, Object> responseData = new HashMap<>();

            try {
                responseData = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
            } catch (Exception ex) {
                System.out.println("Failed to parse error JSON response.");
                ex.printStackTrace();
                // Optionally, you can store the raw error text manually
                responseData.put("rawError", response.getBody());
            }

            System.out.println("statusCode: " + (response != null ? response.getStatusCode() : "null"));
            System.out.println("processId: " + processId);

            throw new Exception(getProcessErrorMessage(responseData));
        }


//        if (response != null) {
//            String body = response.getBody();
//            System.out.println("Raw response body: " + body); // Always log raw body
//
//            if (body != null && body.trim().startsWith("{")) { // Simple JSON detection
//                try {
//                    responseData = mapper.readValue(body, new TypeReference<Map<String, Object>>() {});
//                } catch (Exception e) {
//                    System.err.println("Failed to parse JSON response.");
//                    e.printStackTrace();
//                    // Optional: put raw body in error
//                    responseData.put("status", "error");
//                    responseData.put("raw_response", body);
//                }
//            } else {
//                System.err.println("Response body is not valid JSON.");
//                responseData.put("status", "error");
//                responseData.put("raw_response", body);
//            }
//
//            if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
//                return responseData;
//            } else {
//                System.out.println("statusCode: " + response.getStatusCode());
//                System.out.println("processId: " + processId);
//                throw new Exception(getProcessErrorMessage(responseData));
//            }
//        } else {
//            throw new Exception("Response is null.");
//        }

//        if (response != null && (response.getStatusCode() == 200 || response.getStatusCode() == 201)) {
//            Map<String, Object> responseData = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
//
//            return responseData;
//        } else {
//            Map<String, Object> responseData = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
//            System.out.println("statusCode: " + (response != null ? response.getStatusCode() : "null"));
//            System.out.println("processId: " + processId);
//            throw new Exception(getProcessErrorMessage(response != null ? responseData : null));
//        }
    }

    public static Response postWithResponse(String address, String body, boolean byAdmin) throws Exception {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(address);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            if (byAdmin) {
                conn.setRequestProperty("token", UserContext.adminContext().getPublicKey());
                conn.setRequestProperty("user", UserContext.adminContext().getUserName());
                conn.setRequestProperty("branch", "BR");
            } else {
                conn.setRequestProperty("token", "3dda4374-2434-4414-9a9e-bdc1d621fa25");
                conn.setRequestProperty("user", "9910433274");
                conn.setRequestProperty("branch", "BR");
            }

            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = body.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int statusCode = conn.getResponseCode();
            String responseBody = HttpUtils.readResponse(conn); // You need a helper to read response

            return new Response(statusCode, responseBody);

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private static String getBusinessKey(String processId) {
        // Implement your own logic here
        return "GeneratedBusinessKey_" + processId;
    }

    private static String getProcessErrorMessage(Map<String, Object> responseData) {
        // Implement your own error message extraction from the response
        if (responseData != null && responseData.containsKey("message")) {
            return (String) responseData.get("message");
        }
        return "Unknown error";
    }
}
