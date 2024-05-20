package Game;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author javeria 
 */
public class UsernameManager {
    private static final String FILENAME = "usernames.txt";
    private static final String DELIMITER = ":";
    
    //Save username and password hash to this file
    public static void saveUsername(String username){
    try(PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true))){
        writer.println(username);
    } catch (IOException e) {
        e.printStackTrace();
        }
    }
   
    // Load usernames and password Hashes 
    public static List<String> loadUsernamesAndPasswordHashes()throws FileNotFoundException{
        List<String> usernamesAndPasswordHashes = new ArrayList<>();
        
        try(Scanner scanner = new Scanner(new File(FILENAME))){
            while(scanner.hasNextLine()){
               usernamesAndPasswordHashes.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e){
            System.out.println("Username file not found.");
        }   
        return usernamesAndPasswordHashes; 
    }
    
    
    
    public static boolean verifyPassword(String username, String password) {
        
        try {
            List<String> usernamesAndPasswordHashes = loadUsernamesAndPasswordHashes();
        for (String entry : usernamesAndPasswordHashes) {
            String[] parts = entry.split(DELIMITER);
            if (parts.length == 2 && parts[0].equals(username)) {
                String savedPasswordHash = parts[1]; // hash comparison
                    String providedPasswordHash = PasswordManager.hashPassword(password);
                return savedPasswordHash.equals(providedPasswordHash);
            }
        }
    }   
        catch (FileNotFoundException e){
            System.out.println("File of username not found");
        }
        return false; // Username not found
    }
    
    private static String hashPassword(String password){
        return password;
    }
    
    public static void saveUsername (String username, String hashedPassword){
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true))){
            writer.println(username + DELIMITER + hashedPassword);
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
    