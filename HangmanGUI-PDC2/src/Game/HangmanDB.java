package Game;
/**
 *
 * @author jav
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HangmanDB {
    private static HangmanDB instance;
    private Connection connection;
    private static final String USER_NAME = "hangman";
    private static final String PASSWORD = "hangedman";
    private static final String URL = "jdbc:derby://localhost:1527/HangmanDB";

    private HangmanDB() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); // Ensures correct driver
            this.connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static HangmanDB getInstance() throws SQLException {
        if (instance == null) {
            instance = new HangmanDB();
        } else if (instance.getConnection().isClosed()) {
            instance = new HangmanDB();
        }
        return instance;
    }

    public void createTables() {
        GameConnection.createUsersTable();
        GameConnection.createWordsTable();
    }
}
