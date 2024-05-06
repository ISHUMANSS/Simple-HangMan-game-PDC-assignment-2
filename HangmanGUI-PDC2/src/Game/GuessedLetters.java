package Game;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author javeria
 */
public class GuessedLetters {
    private final Set<Character> guessedLetters;
    
    public GuessedLetters() {
        this.guessedLetters = new HashSet<>();
    }
    
    public boolean guessAdd(char guess) {
        guess = Character.toLowerCase(guess);
        if (guessedLetters.contains(guess)){
            System.out.println("This letter has already been guessed: " + guessedLetters);
            return false; // returns as the letter has always been guessed
        }
            guessedLetters.add(guess);
            return true;
    }
    
    public boolean contains(char guess){
        return guessedLetters.contains(Character.toLowerCase(guess));
    }
    
    public void clear() {
        guessedLetters.clear();
    }
}