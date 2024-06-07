package Game;
/**
 *
 * @author jav
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameConnection {

    private static HangmanDB db;

    public GameConnection() throws SQLException, Exception {
        db = HangmanDB.getInstance();
        createWordsTable();
        createUsersTable();
        populateWordsTable();
        testConnection();
    }

    public static void main(String[] args) {
        try {
            GameConnection gameConnection = new GameConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final List<String> wordList = new ArrayList<>();

    // Add words to the word list
    static {
        wordList.add("juxtaposition");
        wordList.add("text");
        wordList.add("words");
        wordList.add("multiple");
        wordList.add("lollipop");
        wordList.add("large");
        wordList.add("synonym");
        wordList.add("funny");
    }

    // Method to get a random word from the word list
    public static String getRandomWord() {
        if (wordList.isEmpty()) {
            throw new IllegalStateException("Word list is empty or not initialized");
        }
        return wordList.get((int) (Math.random() * wordList.size()));
    }

    // Method to add a word to the word list
    public static void addWord(String word) {
        wordList.add(word);
    }

    // Method to get all words in the word list
    public static List<String> getAllWords() {
        return new ArrayList<>(wordList);
    }

    // Database methods

    private static final String USER_NAME = "hangman";
    private static final String PASSWORD = "hangedman";
    private static final String URL = "jdbc:derby:HangmanDB;create=true";  // Embedded URL

    // Connect to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    // Create tables if not existing already
    public static void createUsersTable() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            if (!doesTableExist(conn, "USERS")) {
                String createUsersTable = "CREATE TABLE USERS ("
                        + "USERNAME VARCHAR(255) PRIMARY KEY, "
                        + "PASSWORD VARCHAR(255))";
                stmt.executeUpdate(createUsersTable);
                System.out.println("Users table created successfully.");
            } else {
                System.out.println("Users table already exists.");
            }
        } catch (SQLException e) {
            System.err.println("Error creating users table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void createWordsTable() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            if (!doesTableExist(conn, "WORDS")) {
                String createWordsTable = "CREATE TABLE WORDS ("
                        + "WORD_ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                        + "WORD VARCHAR(50))";
                stmt.executeUpdate(createWordsTable);
                System.out.println("Words table created successfully.");
            } else {
                System.out.println("Words table already exists.");
            }
        } catch (SQLException e) {
            System.err.println("Error creating words table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to check if a table exists
    private static boolean doesTableExist(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        try (ResultSet res = meta.getTables(null, null, tableName.toUpperCase(), new String[]{"TABLE"})) {
            return res.next();
        }
    }

    // Populate the WORDS table with initial data
    public static void populateWordsTable() {
        String[] words = {"juxtaposition", "text", "words", "multiple", "lollipop", "large", "synonym", "funny"};
        String insertWord = "INSERT INTO WORDS (WORD) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertWord)) {
            for (String word : words) {
                pstmt.setString(1, word);
                pstmt.executeUpdate();
            }
            System.out.println("Words table populated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error populating words table: " + e.getMessage());
        }
    }

    // Add a single word to the WORDS table
    public static void addWordToDB(String word) {
        String sql = "INSERT INTO WORDS (WORD) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, word);
            pstmt.executeUpdate();
            System.out.println("Word added to database: " + word);
        } catch (SQLException e) {
            System.err.println("Error adding word to database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Gather all words from the WORDS table
    public static List<String> getAllWordsFromDB() {
        List<String> words = new ArrayList<>();
        String sql = "SELECT WORD FROM WORDS";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                words.add(rs.getString("WORD"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return words;
    }

    private static void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("Connected to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
