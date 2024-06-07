/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Game;

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
public class WordRandomiserTest {
    
    public WordRandomiserTest() {
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
     * Test of getRandomWord method, of class WordRandomiser.
     */
   
    @Test
    public void testGetRandomWord() {
        
        //Check to see if the random word is a word that was in the set from the database
        System.out.println("Test getRandomWord");
        WordRandomiser instance = new WordRandomiser();
        
        boolean expResult = true;
        boolean result = false;
        Object[] wordsArray = instance.wordList.toArray();
        
        
        String randomWord = instance.randomWord;
        
        for(int i = 0; i < wordsArray.length; ++i ){
            if(wordsArray[i].equals(randomWord)){
                System.out.println("The random word: (" + randomWord + ") was found in the database");
                result = true;
            }
        }
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
