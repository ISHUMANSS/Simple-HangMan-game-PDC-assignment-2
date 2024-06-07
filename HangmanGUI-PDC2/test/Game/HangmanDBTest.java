/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Game;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author javer
 */
public class HangmanDBTest {
    
    public HangmanDBTest() {
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
     * Test of getConnection method, of class HangmanDB.
     */
    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        try{
            HangmanDB instance = HangmanDB.getInstance();
            Connection result = instance.getConnection();
            assertNotNull("The connection cannot be null", result);
            assertTrue("The connection should be true/valid", result.isValid(5));
            System.out.println("getConnection test passed.");
        }   catch (Exception e){
            fail("Error in getConnection test: " + e.getMessage());
        }
    }

    /**
     * Test of createTables method, of class HangmanDB.
     */
    @Test
    public void testCreateTables() {
        System.out.println("createTables");
        try {
            HangmanDB instance = HangmanDB.getInstance();
            instance.createTables();
            System.out.println("createTables test passed.");
        } catch (Exception e) {
            fail("Error in createTables test: " + e.getMessage());
        }
    }
}
