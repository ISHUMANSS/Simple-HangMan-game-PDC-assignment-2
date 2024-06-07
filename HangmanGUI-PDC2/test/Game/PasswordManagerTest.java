package Game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;

/**
 *
 * @author jav
 */
public class PasswordManagerTest {
    
    public PasswordManagerTest() {
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
     * Test of hashPassword method, of class PasswordManager.
     */
    @Test
    public void testHashPassword() {
        System.out.println("hashPassword");
        String password = "mysecretpassword";
        String result = PasswordManager.hashPassword(password);
        assertNotNull(result);
        assertNotEquals(password, result);
    }

    /**
     * Test of verifyPassword method, of class PasswordManager.
     */
    @Test
    public void testVerifyPassword() {
        System.out.println("verifyPassword");
        String password = "mysecretpassword";
        String hashedPassword = PasswordManager.hashPassword(password);
        boolean result = PasswordManager.verifyPassword(password, hashedPassword);
        assertTrue(result);
        
        boolean resultIncorrect = PasswordManager.verifyPassword("wrongpassword", hashedPassword); //test with wrong password
        assertFalse(resultIncorrect);
    }
}
