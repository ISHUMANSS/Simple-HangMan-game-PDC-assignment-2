package Game;
/**
 *
 * @author jav
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HangmanDB {

    private static final String USER_NAME = "hangman";
    private static final String PASSWORD = "hangedman";
    private static final String URL = "jdbc:derby://localhost:1527/HangmanDB";

    private static HangmanDB instance;
    private Connection conn;

    private HangmanDB() {
        establishConnection();
    }

    public static HangmanDB getInstance() {
        if (instance == null) {
            instance = new HangmanDB();
        }
        return instance;
    }

    public Connection getConnection() {
        if (conn == null) {
            establishConnection();
        }
        return this.conn;
    }

    private void establishConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            if (conn != null) {
                System.out.println("Connected to database.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Derby JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed.");
            } catch (SQLException ex) {
                System.err.println("Error closing connection: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
