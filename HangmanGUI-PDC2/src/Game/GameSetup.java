/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

/**
 *
 * @author alist
 */
public class GameSetup {
    
    //needed classes
    public WordRandomiser wordRandomiser;

    
    
    
    //Hangman functions 
    private final int maximumTries = 8;
    public int remainingTries;
    private boolean gameEnded;
    private String word;
    public String underline;
    public int count;
    public boolean win;
    
    public GameSetup(){
        this.wordRandomiser = new WordRandomiser();
        
        //set up the game
        this.remainingTries = maximumTries;
        this.gameEnded = false;
        
        this.word = wordRandomiser.randomWord; //pick the random word for this game
        this.win = false;
        
        initializeUnderline();
        
        
    }
    
    
    private void initializeUnderline() {
        this.underline = new String(new char[word.length()]).replace("\0", "_ ");
    }
    
    public String getWord() {
        return word;
    }

    public int getMaximumTries() {
        return maximumTries;
    }

    public boolean isGameEnded() {
        if(this.remainingTries <= 0){
            gameEnded = true;
        }
        else if(this.win == true){
            gameEnded = true;
        }
        else{
            gameEnded = false;
        }
        
        return gameEnded;
    }
    
}
