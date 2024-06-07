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
public class WordCheckerTest {
    
    public WordCheckerTest() {
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
     * Test of wordCheck method, of class WordChecker.
     */
    @Test
    public void testWordCheck() {
        //used to check that the word checker function works
        //important to check because it is a vital part of the hangman game
        
        System.out.println("Testing wordChecker");
        
        
        WordChecker instance = new WordChecker();
        
        //gets the random word from the database
        String testWord = instance.randomWord; 
        
        boolean didWork = true;
        
        //checks to see if the work checker function works
        //loops through each chracter in the test word
        for(int i = 0;  i < testWord.length(); i++){ 
            Character guess = testWord.charAt(i);
            
            instance.wordCheck(guess); //finds all the times the character turns up in the words
            
            //if any time the word guess outputs the wrong value for the correct 
            //charcter it makes the test fail
            if(!guess.equals(testWord.charAt(i))){
                didWork = false;
            }
            
        }
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
