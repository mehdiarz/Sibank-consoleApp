import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class EncryptHelper {
    public static String encrypt(String plainText, String key1, String key2) throws Exception {
        String encryptedData = "";
        try {
            IvParameterSpec iv = new IvParameterSpec(key2.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(key1.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // PKCS5 = PKCS7 compatible in Java
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
            encryptedData = Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            System.out.println("Encryption error => " + e.getMessage());
        }
        return encryptedData;
    }
}
