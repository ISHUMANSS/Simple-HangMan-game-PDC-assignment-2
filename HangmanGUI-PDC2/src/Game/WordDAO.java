package Game;
/**
 *
 * @author javer
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;  

public class WordDAO {

    /**
     * This adds a word to the database.
     * @param word the word to be added
     */
    public static void addWord(String word) {
        String sql = "INSERT INTO WORDS (WORD) VALUES (?)";
        try (Connection conn = HangmanDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, word);
            pstmt.executeUpdate();
            System.out.println("Word added: " + word);
        } catch (SQLException e) {
            System.err.println("Error adding word: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * retrieves words from the database. 
     * @return a list of words
     */
    public static List<String> getAllWords() {
        String sql = "SELECT WORD FROM WORDS";
        List<String> words = new ArrayList<>();
        try (Connection conn = HangmanDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                words.add(rs.getString("WORD"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching words: " + e.getMessage());
            e.printStackTrace();
        }
        return words;
    }
}
