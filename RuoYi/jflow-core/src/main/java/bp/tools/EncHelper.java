package bp.tools;
import javax.crypto.Cipher;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.CipherOutputStream;
import javax.crypto.CipherInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;

public class EncHelper {
    private static byte[] keys = { 0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF}; // 自定义密钥
    private static String encryptKey = "ccflow123";

    public static boolean encryptDES(String inFile, String outFile) {
        try {
            byte[] rgbKeys = encryptKey.substring(0, 8).getBytes("UTF-8");
            Key key = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(rgbKeys));

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            try (FileInputStream inFs = new FileInputStream(inFile);
                 FileOutputStream outFs = new FileOutputStream(outFile);
                 CipherOutputStream encStream = new CipherOutputStream(outFs, cipher)) {

                byte[] byteIn = new byte[100];
                int bytesRead;

                while ((bytesRead = inFs.read(byteIn)) != -1) {
                    encStream.write(byteIn, 0, bytesRead);
                }
            }
            return true; // 加密成功
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false; // 加密失败
        }
    }

    public static boolean decryptDES(String inFile, String outFile) {
        try {
            byte[] rgbKeys = encryptKey.substring(0, 8).getBytes("UTF-8");
            Key key = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(rgbKeys));

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);

            try (FileInputStream inFs = new FileInputStream(inFile);
                 FileOutputStream outFs = new FileOutputStream(outFile);
                 CipherInputStream decStream = new CipherInputStream(inFs, cipher)) {

                byte[] byteIn = new byte[100];
                int bytesRead;

                while ((bytesRead = decStream.read(byteIn)) != -1) {
                    outFs.write(byteIn, 0, bytesRead);
                }
            }
            return true; // 解密成功
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false; // 解密失败
        }
    }

    public static void main(String[] args) {
        // 示例用法
        String inputFile = "path/to/input/file.txt"; // 输入文件路径
        String encryptedFile = "path/to/encrypted/file.txt"; // 加密后文件路径
        String decryptedFile = "path/to/decrypted/file.txt"; // 解密后文件路径

        if (encryptDES(inputFile, encryptedFile)) {
            System.out.println("加密成功");
        } else {
            System.out.println("加密失败");
        }

        if (decryptDES(encryptedFile, decryptedFile)) {
            System.out.println("解密成功");
        } else {
            System.out.println("解密失败");
        }
    }
}