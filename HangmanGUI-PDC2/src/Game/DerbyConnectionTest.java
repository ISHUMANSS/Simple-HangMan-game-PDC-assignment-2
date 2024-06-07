/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author javer
 */
public class DerbyConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:derby://localhost:1527/HangmanDB";
        String user = "hangman";
        String password = "hangedman";
        
        Connection conn = null;
        
        try {
            // Load the Derby JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            // Establish the connection
            conn = DriverManager.getConnection(url, user, password); // Use local variables
            if (conn != null) {
                System.out.println("Connected to database.");
                conn.close();
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
