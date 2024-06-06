/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Game;

import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alist
 */
public class ReadFileTest {
    
    public ReadFileTest() {
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
     * Test of read method, of class ReadFile.
     */
    //used to test the Read file
    //tests to see if the words in the set are the same as in the database
    @Test
    public void testRead() {
       
        System.out.println("Test read words from database");
        ReadFile instance = new ReadFile();
        
        //gets all the the words form the database
        HashSet<String> basicWords = instance.read();
        HashSet<String> testingWords = instance.read();
        Object[] wordsArray = testingWords.toArray();
        
        boolean result = false;
        boolean expResult = true;
        //checks that the the words that occure in testing words are also in the set from the database
        for(int i = 0; wordsArray.length > i; ++ i){
            if(basicWords.contains(wordsArray[i])){
                result = true;
            }
            else{
                result = false;
            }
        }
        
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
