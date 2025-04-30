import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class test {

private static final byte[] kbq = new byte[]{53, 108, 71, 69, 105, 90, 106, 119, 68, 85, 116, 56, 85, 51, 54, 48};
private static final byte[] ibq = new byte[]{83, 114, 97, 68, 113, 50, 79, 83, 102, 68, 74, 53, 65, 51, 56, 107};

    public static String decrypt(String encrypted) throws Exception {



            IvParameterSpec iv = new IvParameterSpec(ibq);
            SecretKeySpec secretKeySpec = new SecretKeySpec(kbq, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(2, secretKeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            String decryptedData = new String(original, StandardCharsets.UTF_8);
            return decryptedData;
    }

        }