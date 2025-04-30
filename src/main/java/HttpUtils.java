import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    // Method to read the response from HttpURLConnection
    public static String readResponse(HttpURLConnection conn) throws IOException {
        StringBuilder response = new StringBuilder();
        int status = conn.getResponseCode();

        // Check if the response is 200 or 2xx (OK)
        if (status == HttpURLConnection.HTTP_OK || status == HttpURLConnection.HTTP_CREATED) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
        } else {
            // Handle error response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
        }
        return response.toString(); // Return the response body as string
    }

    // Method to create the HttpURLConnection and perform the request
    public static HttpURLConnection createConnection(String urlStr, String method) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true); // Allow sending data in the request body
        return conn;
    }

    // Example of sending a POST request using HttpURLConnection
    public static String sendPostRequest(String urlStr, String bodyJson) throws IOException {
        HttpURLConnection conn = createConnection(urlStr, "POST");
        conn.getOutputStream().write(bodyJson.getBytes());

        // Get the response body from the connection
        return readResponse(conn);
    }
}
