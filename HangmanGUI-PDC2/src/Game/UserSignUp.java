package Game;
/**
 *
 * @author jav
 */ 
import java.util.Scanner;

public class UserSignUp{
    
    public static boolean signUp(String username, String password) {
        if (isValidUsername(username) && isValidPassword(password)) {
            String hashedPassword = PasswordManager.hashPassword(password);
            System.out.println("Username and password are valid. Attempting to add user to database.");
            UserDAO.addUser(username, hashedPassword);
            System.out.println("Sign up has been successful");
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }

    private static boolean isValidUsername(String username) {
        return !username.isEmpty();
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
}