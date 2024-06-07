/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Game;

import java.sql.Connection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jav
 */
public class GameConnectionTest {
    
    public GameConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of getConnection method, of class GameConnection.
     */
    @Test
    public void testConnection() {
        try {
            GameConnection gameConnection = new GameConnection();
            assertNotNull(gameConnection);
            assertNotNull(GameConnection.getConnection());
            System.out.println("Database connection test passed.");
        } catch (Exception e) {
            fail("Database connection test failed: " + e.getMessage());
        }
    }

    /**
     * Test of populateWordsTable method, of class GameConnection.
     */
    @Test
    public void testPopulateWordsTable() {
        System.out.println("populateWordsTable");
        try {
            GameConnection gameConnection = new GameConnection();
            GameConnection.populateWordsTable();

            List<String> allWords = GameConnection.getAllWordsFromDB();
            assertNotNull("List of words shouldn't be null", allWords);
            assertFalse("List of words shouldn't be empty", allWords.isEmpty());
            assertTrue("List of words should contain at least one word", allWords.size() > 0);
            System.out.println("populateWordsTable test passed.");
        } catch (Exception e) {
            fail("Error in populateWordsTable test: " + e.getMessage());
        }
    }

    
    
}
