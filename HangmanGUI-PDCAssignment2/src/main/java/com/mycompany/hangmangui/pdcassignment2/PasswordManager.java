package com.mycompany.hangmangui.pdcassignment2;

/**
 *
 * @author javeria
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordManager {
    
    public static String hashPassword(String password) { 
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); //password hash using SHA-256
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return hashPassword(password).equals(hashedPassword);
    }
}
