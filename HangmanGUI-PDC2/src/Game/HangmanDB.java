package Game;
/**
 *
 * @author jav
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HangmanDB {
    private static HangmanDB instance;
    private Connection connection;
    private static final String USER_NAME = "hangman";
    private static final String PASSWORD = "hangedman";
    private static final String URL = "jdbc:derby:HangmanDB;create=true";  // Embedded URL

    private HangmanDB() throws SQLException {
        try {
            // No need to start the server for embedded mode
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed to connect to the database: " + ex.getMessage());
        }
    }

    public static HangmanDB getInstance() throws SQLException {
        if (instance == null) {
            instance = new HangmanDB();
        } else if (instance.getConnection().isClosed()) {
            instance = new HangmanDB();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public void createTables() {
        GameConnection.createUsersTable();
        GameConnection.createWordsTable();
    }
}
