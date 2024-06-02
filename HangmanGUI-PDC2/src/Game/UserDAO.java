package Game;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author javeria 
 */
public class UserDAO {
    
    private Connection conn;
    
   
    public UserDAO(Connection conn) {
        this.conn = conn;
    }
    
    public boolean registerUser(String username, String password) {
        try {
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO Users (username, password) VALUES ('" + username + "', '" + password + "')";
            int rowsInserted = statement.executeUpdate(sql);
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean authenticateUser(String username, String password) {
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM Users WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}