package Game;
/**
 *
 * @author jav
 */ 
import java.util.Scanner;

public class UserSignUp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void signUp() {
        System.out.println("Sign up for a Hangman Account!!");
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        if (isValidUsername(username)) {
            if (isValidPassword(password)) {
                String hashedPassword = PasswordManager.hashPassword(password);
                System.out.println("Username and password are valid. Attempting to add user to database.");
                UserDAO.addUser(username, hashedPassword);
                System.out.println("Sign up has been successful");
            } else {
                System.out.println("Password must be at least 6 characters long. Please try again.");
            }
        } else {
            System.out.println("Invalid username. Please try again.");
        }
    }

    private static boolean isValidUsername(String username) {
        return !username.isEmpty();
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
}