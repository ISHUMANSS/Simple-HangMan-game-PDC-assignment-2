package Game;
/**
 *
 * @author jav
 */

public class WordManager {

    public void write(String input) {
        String wordToAdd = oneWordLimiter(input).toLowerCase().trim();
        if (!wordToAdd.equals("")) {
            WordDAO.addWord(wordToAdd);
        } else {
            System.out.println("Word cannot be added");
        }
    }

    // only one word, no sentence
    public String oneWordLimiter(String input) {
        StringBuilder firstWord = new StringBuilder();
        
        for (int i = 0; i < input.trim().length(); i++) {
            if (input.charAt(i) == ' ') {
                System.out.println("Please only input one word at a time. The first word inputted to the file.");
                return firstWord.toString(); // return first word
            } else {
                if (Character.isLetter(input.charAt(i))) {
                    firstWord.append(input.charAt(i));
                } else {
                    System.out.println("Non-letter character found.");
                    return "";
                }
            }
        }
        return firstWord.toString(); // one word found
    }
}
