/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author alist
 */

//allows the user to write to the file
public class FileWriter {
    PrintWriter pw = null;
    
    public void write(String input){
        try{
            String wordToAdd = oneWordLimiter(input).toLowerCase().trim();
            if(!wordToAdd.equals("")){
                try{
                    pw = new PrintWriter(new FileOutputStream("./resources/word_bank.txt",true)); //appends the rather then replaces the text
                    pw.println(wordToAdd); //will print the inputed text to the file
                    pw.close();
                }
                catch(FileNotFoundException e){
                    System.out.println(e.getMessage());
                }
            }
            else{
                System.out.println("Word couldn't be added");
            }
        }
        catch(NullPointerException e){
            System.out.println("Word couldn't be added becasue null was given");
        }
    }
    //using the hash set in the file reader file makes it so that words are allowed to be added to the set multiple times without it messing with how many times it turns up
    
    
    //Make it so it can only write one word and not a sentance
    public String oneWordLimiter(String input){
        String firstWord = "";
        
        for(int i = 0; i < input.trim().length(); i++){
            
            if(input.charAt(i) == ' '){
                System.out.println("Please only input one word at a time. First word imputed to the file");
                return firstWord;//multiple words were found return the first word
            }
            else{
                if(Character.isLetter(input.charAt(i))){
                    firstWord = firstWord + input.charAt(i);
                }
                else{
                    System.out.println("There was a non leter character found ");
                    return "";
                }
            }

        }
        return firstWord; //only one word was found
    }   
    
    
    
    //deleat all the words in the file and add the new word
    public void replaceAllWords(String input){
        try{
            String wordToAdd = oneWordLimiter(input).toLowerCase().trim();
            if(!wordToAdd.equals("")){
                try{
                    pw = new PrintWriter(new FileOutputStream("./resources/word_bank.txt")); //rewrites all of the words in the file and only has the current one
                    pw.println(oneWordLimiter(input).toLowerCase().trim()); //will print the inputed text to the file
                    pw.close();
                }
                catch(FileNotFoundException e){
                    System.out.println(e.getMessage());
                }
            }
            else{
                System.out.println("Word couldn't be added");
            }
        }
        catch(NullPointerException e){
            System.out.println("Word couldn't be added becasue null was given");
        }
    }
    
}