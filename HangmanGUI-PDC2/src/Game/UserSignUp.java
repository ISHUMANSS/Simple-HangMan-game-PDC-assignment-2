package Game;
/**
 *
 * @author javer
 */

public class UserSignUp {
    //private static final Scanner scanner = new Scanner(System.in);
    
    public static void signUp(String userName, String Password){
        //old code to be replaced
//        System.out.println("Sign up for a Hangman Account!!");
//        System.out.println("Username: ");
//        String username = scanner.nextLine();
//        System.out.println("Password: ");
//        String password = scanner.nextLine();
        
        String username = userName;
        String password = Password;
        
        if (isValidUsername(username) && isValidUsername(password)){
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