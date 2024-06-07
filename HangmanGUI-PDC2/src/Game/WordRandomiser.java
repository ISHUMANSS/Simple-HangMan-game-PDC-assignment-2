package Game;
/**
 *
 * @author alist jav
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordRandomiser {
    List<String> wordList;

    // Randomly picks a word from the word list and uses that as the word for the game.
    public WordRandomiser() {
        wordList = new ArrayList<>();
        loadWordsFromDatabase();
        if (wordList.isEmpty()) {
            throw new IllegalStateException("Word list is empty or not initialized");
        }
    }

    // Load words from the database
    private void loadWordsFromDatabase() {
        String sql = "SELECT WORD FROM WORDS";
        try (Connection conn = HangmanDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                wordList.add(rs.getString("WORD"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String pickRandom() {
        if (wordList.isEmpty()) {
            throw new IllegalStateException("Word list is empty or not initialized");
        }
        return wordList.get((int) (Math.random() * wordList.size()));
    }
}