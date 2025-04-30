import java.util.HashMap;
import java.util.Map;

public class SecureStorage {
    private static final Map<String, String> storage = new HashMap<>();

    public static void write(String key, String value) {
        storage.put(key, value);
    }

    public static String read(String key) {
        return storage.get(key);
    }

    public static void delete(String key) {
        storage.remove(key);
    }
}
