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

    public boolean verifyPassword(String username, String password) {
        String sql = "SELECT PASSWORD FROM USERS WHERE USERNAME = ?";
        try (Connection conn = HangmanDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("PASSWORD");
                    return PasswordManager.verifyPassword(password, storedHash); // Assumes verifyPassword is implemented correctly
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error verifying password: " + e.getMessage());
        }
        return false;
    }
}