package Game;
/**
 *
 * @author alist jav
 */
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * Takes in all the words from the file and picks a random one.
 */
public class WordRandomiser extends ReadFile { 
    String randomWord;
    
    // Randomly picks a word from the word list and uses that as the word for the game.
    public WordRandomiser() {
        super();
        this.randomWord = pickRandom();
    }
    
    // Randomly picks a word from the list.
    private String pickRandom() {
        if (this.wordList == null || this.wordList.isEmpty()) {
            throw new IllegalStateException("Word list is empty or not initialized");
        }

        List<String> words = new ArrayList<>(this.wordList);
        int size = words.size();
        int item = new Random().nextInt(size);
        return words.get(item);
    }
    
    public String getRandomWord() {
        return randomWord;
    }
}
