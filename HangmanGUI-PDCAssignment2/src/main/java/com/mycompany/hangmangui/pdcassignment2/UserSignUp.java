package com.mycompany.hangmangui.pdcassignment2;

import java.util.Scanner;

/**
 *
 * @author javer
 */
public class UserSignUp {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void signUp(){
        System.out.println("Sign up for a Hangman Account!!");
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        
        if (isValidUsername(username) && isValidUsername(password)){
            String hashedPassword = PasswordManager.hashPassword(password);
            UsernameManager.saveUsername(username, hashedPassword);
            
            System.out.println("Sign up successful ");
        }   else {
            System.out.println("Please try again. Invalid username and/or password.");
        }
    }
    
    private static boolean isValidUsername(String username){
        return !username.isEmpty();
    }
    
    private static boolean isValidPassword(String password){
        return password.length() >= 6;
    }
      
    private static void storeCredentials(String username, String hashedPassword){
        System.out.println("Storing username and password...");
    }
    
}
