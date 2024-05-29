package Game;
/**
 *
 * @author javer
 */
import java.util.Scanner;

public class UserSignUp{
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void signUp(){
        System.out.println("Sign up for a Hangman Game Account!!");
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        if (isValidUsername(username) && isValidPassword(password)) {
            String hashedPassword = PasswordManager.hashPassword(password);
            UserDAO.addUser(username, hashedPassword);

            System.out.println("Sign up has been successful");
        } else {
            System.out.println("Please try again. Invalid username and/or password.");
        }
    }

    private static boolean isValidUsername(String username) {
        return !username.isEmpty();
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
}