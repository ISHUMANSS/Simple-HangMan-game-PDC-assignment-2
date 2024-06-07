package Game;
/**
 *
 * @author jav
 */
import java.util.Scanner;
import java.sql.SQLException;

public class HangmanGame {
    private boolean isLoggedIn = false;
    private String word;
    private String underline;
    private int count;
    private final int maximumTries;
    private int remainingTries;
    private boolean gameEnded;
    private final Scanner scanner;
    private final GuessedLetters guessedLetters;
    private final HangmanDisplay hangmanDisplay;
    private final Username username;
    private final UsernameManager usernameManager;
    private final WordRandomiser wordRandomiser;

    public HangmanGame(int maximumTries, String inputUsername, UsernameManager usernameManager) {
        this.maximumTries = maximumTries;
        this.remainingTries = maximumTries;
        this.gameEnded = false;
        this.scanner = new Scanner(System.in);
        this.guessedLetters = new GuessedLetters();
        this.hangmanDisplay = new HangmanDisplay();
        this.username = new Username(inputUsername);
        this.usernameManager = usernameManager;
        this.wordRandomiser = new WordRandomiser(); // Initialize WordRandomiser
        selectRandomWord(); // Pick a random word for the game
        initializeUnderline();
    }

    private void selectRandomWord() {
        this.word = GameConnection.getRandomWord();
    }


    private void initializeUnderline() {
        this.underline = new String(new char[word.length()]).replace("\0", "_ ");
    }
    
    private static void addWord(Scanner scanner) {
        System.out.println("Enter new word to add to the database:");
        String word = scanner.nextLine().trim();
        GameConnection.addWordToDB(word); //adds to database
    }


    private void hang(String guess) {
        guess = guess.toLowerCase().trim();

        if (!guessedLetters.guessAdd(guess.charAt(0))) {
            return;
        }

        StringBuilder newUnderline = new StringBuilder(underline);

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(0)) {
                newUnderline.setCharAt(i * 2, guess.charAt(0));
            }
        }

        if (!word.contains(guess)) {
            count++;
            hangmanDisplay.incrementCount();
            remainingTries--;
            System.out.println("\nIncorrect letter. Remaining tries are: " + remainingTries);
        } else {
            underline = newUnderline.toString();
        }
        hangmanDisplay.displayHangman();
    }

    private void authenticateUser() {
        boolean authenticated = false;
        while (!authenticated) {
            System.out.print("Enter Username: ");
            String usernameInput = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            if (usernameManager.verifyPassword(usernameInput, password)) {
                authenticated = true;
                isLoggedIn = true;
                System.out.println("Login successful, welcome!");
            } else {
                System.out.println("Incorrect username and/or password. Please try again.");
            }
        }
    }

    public Username getUsername() {
        return username;
    }

    public void reset() {
        this.word = null;
        this.underline = null;
        this.count = 0;
        this.remainingTries = this.maximumTries;
        this.gameEnded = false;
        this.guessedLetters.clear();
        selectRandomWord();
        initializeUnderline();
    }

    public static void main(String[] args) {
      
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        UsernameManager usernameManager = new UsernameManager();
        String randomWord = GameConnection.getRandomWord(); // Get a random word from the database
        HangmanGame game = new HangmanGame(8, randomWord, usernameManager);
        
        while (isRunning) {
            System.out.println("\nGame has started!");
            System.out.println("What would you like to do?");
            System.out.println("1. Sign up");
            System.out.println("2. Sign in");
            System.out.println("3. Start the game");
            System.out.println("4. Add a word to the database");
            System.out.println("5. Quit\n");
            
            String answer = scanner.nextLine();
            switch (answer.toLowerCase()) {
                case "1":
                    System.out.print("Enter Username: ");
                    String usernameInput = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    UserSignUp.signUp(usernameInput, password);
                    break;
                    
                case "2":
                    game.authenticateUser();
                    break;
                    
                case "3":
                    if (game.isLoggedIn) {
                        if (!game.gameEnded && game.remainingTries > 0) {
                            gameLoop(scanner, game);
                        }
                    } else {
                        System.out.println("Please sign in first.");
                    }
                    break;
                    
                case "4":
                    addWord(scanner);
                    break;
                    
                case "5":
                    isRunning = false;
                    break;
                    
                default:
                    System.out.println("That wasn't one of the options. Please input again!");
            }
        }
        scanner.close();
    }

    private static void gameLoop(Scanner scan, HangmanGame game) {
        boolean playAgain = true;
        while (playAgain) {
            while (!game.gameEnded && game.remainingTries > 0) {
                System.out.println("\nEnter a letter:");
                System.out.println(game.underline);
                String guess = scan.nextLine().toLowerCase();
                if (guess.length() != 1 || !Character.isLetter(guess.charAt(0))) {
                    System.out.println("Invalid input. Please enter a single letter.");
                    continue;
                }
                game.hang(guess);

                if (game.underline.replace(" ", "").equals(game.word)) {
                    System.out.println("\nWELL DONE! \nYou have guessed successfully! \nThe word was: " + game.word);
                    game.gameEnded = true;
                }
            }

            if (!game.gameEnded) {
                System.out.println("\nGAME OVER! You've run out of tries. The word was: " + game.word);
            }

            System.out.println("Do you want to play again? (yes/no)");
            String playAgainAnswer = scan.nextLine();
            if (!playAgainAnswer.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }
    }
}
