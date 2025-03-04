
import java.io.InputStream;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Main {

    public static void main(String[] args) {
        findAndDecryptKey();
    }

    public static void findAndDecryptKey() {
        try {
            InputStream dictionaryFile = Main.class.getResourceAsStream("/10th.txt");
            Scanner scanner = new Scanner(dictionaryFile);
            String targetCiphertext = "764aa26b55a4da654df6b19e4bce00f4ed05e09346fb0e762583cb7da2ac93a2";

            while (scanner.hasNextLine()) {
                String potentialKey = scanner.nextLine().trim();
                potentialKey = formatKey(potentialKey);
                byte[] ivBytes = convertHexToBytes("aabbccddeeff00998877665544332211");
                String plaintext = "This is a top secret.";
                byte[] encryptedData = encryptData(plaintext, ivBytes, potentialKey);
                if (encryptedData == null) continue;

                String encryptedHex = convertBytesToHex(encryptedData).toLowerCase();
                if (encryptedHex.equals(targetCiphertext)) {
                    String decryptedText = decryptData(encryptedData, ivBytes, potentialKey);
                    if (decryptedText != null) {
                        System.out.println("Decrypted Text: " + decryptedText);
                        System.out.println("Key in ASCII: " + potentialKey);
                        System.out.println("Key in Hex: " + convertBytesToHex(potentialKey.getBytes()));
                        System.out.println("Encrypted Hex: " + encryptedHex);
                    }
                    break;
                }
            }
            scanner.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String formatKey(String key) {
        if (key.length() > 16) {
            key = key.substring(0, 16);
        }
        if (key.length() < 16) {
            StringBuilder keyBuilder = new StringBuilder(key);
            while (keyBuilder.length() < 16) {
                keyBuilder.append("#");
            }
            key = keyBuilder.toString();
        }
        return key;
    }

    public static byte[] convertHexToBytes(String hex) {
        int length = hex.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    public static byte[] encryptData(String plaintext, byte[] iv, String key) {
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(plaintext.getBytes());
        } catch (Exception ex) {
            return null;
        }
    }

    public static String decryptData(byte[] encrypted, byte[] iv, String key) {
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] original = cipher.doFinal(encrypted);
            return new String(original);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String convertBytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
