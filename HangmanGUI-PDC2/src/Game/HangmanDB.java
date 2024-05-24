package Game;

/**
 *
 * @author javer
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class HangmanDB {
    
    private static final String USER_NAME = "hangman";
    private static final String PASSWORD = "hangedman";
    private static final String URL = "jdbc:derby://localhost:1527/HangmanDB";
    
    Connection conn;
    
    public HangmanDB(){
        establishConnection();
    }
    
    public static void main(String[] args) {
        HangmanDB hangmanDb = new HangmanDB();
        if(hangmanDb.getConnection() != null){
            System.out.println("Successfully connected to the hangman database");
        }   else {
            System.out.println("Failed to connect to hangman database");
        }
        hangmanDb.closeConnections();
    }
    
    public Connection getConnection(){
        if (conn == null){
            establishConnection();
        }
            return this.conn;
    }
    
    public void establishConnection(){
        try{
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            if (conn != null){
                System.out.println("Connected to database.");
            }
        }   catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void closeConnections(){
        if (conn != null){
            try{
                conn.close();
            }   catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public ResultSet queryDB(String sql){
        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }
    
    public void updateDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

