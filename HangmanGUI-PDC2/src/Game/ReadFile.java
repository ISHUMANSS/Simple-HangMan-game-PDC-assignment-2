package Game;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 *
 * @author alist
 */

//Reads all the words on the file 
public class ReadFile {
    
    HashSet<String> wordList;
    
    public ReadFile(){
        this.wordList = read();
    }    
    
    public HashSet<String> read(){ 
    //using hashset makes it so even if there are any dupes in the file it only makes the items turn up onece in the file
        HashSet<String> wordsList = new HashSet<String>(); //used to make sure all Strings are unique
        try{
            FileReader fr = new FileReader("./resources/word_bank.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line = null;
            while((line=inputStream.readLine())!= null){ //loops through all the words in the file
                wordsList.add(line); //adds words to the set
            }
            inputStream.close();
            }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        catch(IOException e) {
            System.out.println("Error reading from file " );
        }
        return wordsList;
    }
    
}
