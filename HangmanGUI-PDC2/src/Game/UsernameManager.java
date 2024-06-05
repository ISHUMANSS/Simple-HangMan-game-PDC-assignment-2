package Game;
/**
 *
 * @author jav
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsernameManager {

    public Username username;

    public UsernameManager() {
        this.username = null;
    }

    public UsernameManager(Username username) {
        this.username = username;
    }

    public static void saveUsername(String username, String hashedPassword) {
        String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";
        try (Connection conn = HangmanDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
            System.out.println("Username saved: " + username);
        } catch (SQLException e) {
            System.err.println("Error saving username: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean verifyPassword(String username, String password) {
        String sql = "SELECT PASSWORD FROM USERS WHERE USERNAME = ?";
        try (Connection conn = HangmanDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String savedPasswordHash = rs.getString("PASSWORD");
                    String providedPasswordHash = PasswordManager.hashPassword(password);
                    return savedPasswordHash.equals(providedPasswordHash);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error verifying password: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    public static List<String> loadUsernamesAndPasswordHashes() {
        String sql = "SELECT USERNAME, PASSWORD_HASH FROM USERS";
        List<String> usernamesAndPasswordHashes = new ArrayList<>();
        try (Connection conn = HangmanDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String username = rs.getString("USERNAME");
                String passwordHash = rs.getString("PASSWORD_HASH");
                usernamesAndPasswordHashes.add(username + ":" + passwordHash);
            }
        } catch (SQLException e) {
            System.err.println("Error loading usernames: " + e.getMessage());
            e.printStackTrace();
        }
        return usernamesAndPasswordHashes;
    }

    private static String hashPassword(String password) {
        return password;
    }

    public boolean authenticateUser(String userName, String password) {
        boolean authenticated = false;

        if (this.verifyPassword(userName, password)) {
            authenticated = true;
            System.out.println("Login successful, welcome!");
            if (username != null) {
                username.setUsername(userName);
            }

            return authenticated;
        } else {
            System.out.println("Incorrect username and/or password. Please try again.");
            return authenticated;
        }
    }
}
