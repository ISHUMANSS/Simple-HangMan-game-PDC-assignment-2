package Game;
/**
 *
 * @author alist javer
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;


//Reads all the words on the file 
public class ReadFile {
    
    HashSet<String> wordList;
    
    public ReadFile(){
        this.wordList = read();
    }    
    
    public HashSet<String> read(){ 
        HashSet<String> wordsList = new HashSet<String>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // Establish database connection
            conn = DriverManager.getConnection("jdbc:derby:HangmanDB;create=true", "hangman", "hangedman");
            
            // Prepare SQL statement
            String sql = "SELECT WORD FROM WORDS";
            pstmt = conn.prepareStatement(sql);
            
            // Execute query
            rs = pstmt.executeQuery();
            
            // Process result set
            while (rs.next()) {
                String word = rs.getString("WORD");
                wordsList.add(word);
            }
        } catch (SQLException e) {
            System.out.println("Error reading words from database: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing database resources: " + e.getMessage());
            }
        }
        
        return wordsList;
    }
}
