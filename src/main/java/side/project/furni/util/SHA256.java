package side.project.furni.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SHA256 {

    public static String encrypt(String password) {
        MessageDigest md;

        try {

            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return bytesToHex(md.digest());
    }

    private static String bytesToHex(byte[] bytes) {

        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}
