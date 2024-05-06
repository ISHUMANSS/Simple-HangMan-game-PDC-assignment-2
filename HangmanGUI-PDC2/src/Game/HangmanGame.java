package Game;

import java.util.Scanner;

public class HangmanGame {
    private final boolean isLoggedIn = false;
    private final String[] words;
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
    
    public HangmanGame(String[] words, int maximumTries, String inputUsername, UsernameManager usernameManager) {
        this.words = words;
        this.maximumTries = maximumTries;
        this.remainingTries = maximumTries;
        this.gameEnded = false;
        this.scanner = new Scanner(System.in); 
        this.guessedLetters = new GuessedLetters();
        this.hangmanDisplay = new HangmanDisplay();
        this.username = new Username(inputUsername);
        this.usernameManager = usernameManager;
        selectRandomWord();
        initializeUnderline();
    }
    
   private void selectRandomWord() {
        this.word = words[(int) (Math.random() * words.length)];
    }

    private void initializeUnderline() {
        this.underline = new String(new char[word.length()]).replace("\0", "_ ");
    }
    
    private void hang(String guess) {
        guess = guess.toLowerCase().trim();
    
        if(!guessedLetters.guessAdd(guess.charAt(0))){
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
        WordRandomiser wr = new WordRandomiser();
        String[] words = wr.wordList.toArray(new String[0]);
        FileWriter fw = new FileWriter();

    boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        UsernameManager usernameManager = new UsernameManager();
        HangmanGame game = new HangmanGame(words, 8, "your_username", usernameManager);

    for (int i = 0; i < words.length; i++) {
        words[i] = words[i].toLowerCase();
    }

    try (Scanner scan = new Scanner(System.in)) {
        while (isRunning) {
            System.out.println("\nGame has started!");
            System.out.println("What would you like to do?");
            System.out.println("1. Sign up");
            System.out.println("2. Sign in");
            System.out.println("3. Start the game");
            System.out.println("4. Add a word to the file");
            System.out.println("5. Quit\n");

            String answer = scanner.nextLine();
            switch (answer.toLowerCase()) {
                case "1":
                    UserSignUp.signUp();
                    break;

                case "2":
                    game.authenticateUser();
                    break;

                case "3":
                    if (!game.gameEnded && game.remainingTries > 0) {
                        gameLoop(scan, game);
                    }
                    break;

                case "4":
                    System.out.println("Would you like to replace all the text in the file? (yes/no)");
                    String addTextAnswer = scan.nextLine();
                    if (addTextAnswer.equalsIgnoreCase("yes")) {
                        System.out.println("Replacing all text in file:");
                        System.out.println("What word would you like to add:");
                        String text = scan.nextLine();
                        fw.replaceAllWords(text);
                    } else if (addTextAnswer.equalsIgnoreCase("no")) {
                        System.out.println("Import text you want to add to the file:");
                        String text = scan.nextLine();
                        fw.write(text);
                    } else {
                        System.out.println("Please enter 'yes' or 'no'.");
                    }
                    break;


                case "5":
                    isRunning = false;
                    break;

                default:
                    System.out.println("That wasn't one of the options. Please input again!");
            }
        }   
    }
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
        } else {
            game.reset();
        }
    }
}

    
}
