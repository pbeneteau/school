package school_admin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String encryptPassword(String password) {

        String encryptedPassword = null;

        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(password.getBytes());
            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff), 16).substring(1));

            }
            // Get complete hashed password in hex format
            encryptedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        System.out.println(encryptedPassword);
        return encryptedPassword;
    }

    public static double roundUp(double d) {
        return d > (int) d ? (int) d + 1 : d;
    }

    public enum Role {
        etudiant,
        professeur,
        admin
    }
}
