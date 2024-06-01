/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

/**
 *
 * @author alist
 */
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


//Frame that sets up the game
public class GUIFrame extends JFrame implements ActionListener{
   
    
    public int WIDTH = 700;
    public int HEIGHT = 700;
    public String TITLE = "Hangman Game";
    
    
    //Labels
    public JLabel hangmanWord;
    public JLabel lives;
    public JLabel gameOverLabel;
    
    //Fonts
    public Font hangmanWordFont;
    public Font livesFont;
    public Font gameOverFont;
    
    //buttons
    private static final String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R","S", "T", "U", "V", "W", "X", "Y", "Z" };
    public JButton [] alphabetButtons = new JButton[letters.length];
    
    
    //Set up other needed classes
    public HangmanDisplay hangmanDisplay;
    public GraphicalHanger hangmanImage;
    public GameSetup gameSetup;
    
    public GuessedLetters guessedLetters;
    
    
    
    //pannels
    JPanel center;
    JPanel northPanel;
    
    //constructor that makes all the components work
    public GUIFrame(){
        initComponents();
        initGame();
        initPanels();
        initActionListener();
        
    }
    
    public void initGame(){
        //set up the other needed classes
        this.hangmanDisplay = new HangmanDisplay();
        this.hangmanImage = new GraphicalHanger(hangmanDisplay);
        this.gameSetup = new GameSetup();
        
        this.guessedLetters = new GuessedLetters();
        
        this.hangmanWord.setText(gameSetup.underline);
        
    }
    
    public void initComponents(){
        //set up the fonts
        this.hangmanWordFont = new Font("Calibri", Font.BOLD, 30);
        this.livesFont = new Font("Calibri", Font.PLAIN, 20);
        this.gameOverFont = new Font("Calibri", Font.BOLD, 30);
        
        //sets up all the components
        this.hangmanWord = new JLabel("HangMan word", SwingConstants.CENTER);
        this.hangmanWord.setFont(hangmanWordFont);
        this.lives = new JLabel("Lives left: 8", SwingConstants.CENTER);//how many lifes left
        this.lives.setFont(livesFont);
        this.gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        this.gameOverLabel.setFont(gameOverFont);
        
        //set up alphabet buttons
        for(int i = 0; i < letters.length; ++i){
            this.alphabetButtons[i] = new JButton(letters[i]);
            
        }
        
        //makes the JFrame
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        int frameWidth=screenWidth/2;
        int frameHeight=screenHeight - 60;
        this.setSize(frameWidth, frameHeight);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle(TITLE);
    }
    
    public void initPanels(){
        //north Panel
        this.northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,1));
        northPanel.add(hangmanWord);
        northPanel.add(lives);
        
        this.add(northPanel, BorderLayout.NORTH);
        
        //Center Pannel
        //Buttons part of center
        this.center = new JPanel();
        center.setLayout(new GridLayout(2,1));
        JPanel alphaButtonsPannel = new JPanel();
        alphaButtonsPannel.setLayout(new GridLayout(2, 13));
        for(int i = 0; i < alphabetButtons.length; i++){
            alphaButtonsPannel.add(alphabetButtons[i]);
        }
        //image part of center
        center.add(hangmanImage, BorderLayout.NORTH);
        center.add(alphaButtonsPannel);
        
        this.add(center, BorderLayout.CENTER);
        
      
    }
    
    public void initActionListener(){
        //assigns all the action listeners
        
        for (JButton alphabetButton : alphabetButtons) {
            alphabetButton.addActionListener(this);
        }
        
        
    }
    
    private boolean hang(String guess){
        if(!gameSetup.isGameEnded()){
            guess = guess.toLowerCase().trim();
            if(!guessedLetters.guessAdd(guess.charAt(0))){
                return false;
            }

            StringBuilder newUnderline = new StringBuilder(gameSetup.underline);

            for (int i = 0; i < gameSetup.getWord().length(); i++) {
                if (gameSetup.getWord().charAt(i) == guess.charAt(0)) {
                    newUnderline.setCharAt(i * 2, guess.charAt(0));
                }
            }

            if (!gameSetup.getWord().contains(guess)) { 
                //gameSetup.count++;
                hangmanDisplay.incrementCount();
                gameSetup.remainingTries--;
                System.out.println("\nIncorrect letter. Remaining tries are: " + gameSetup.remainingTries);
                lives.setText("Lives left: " + gameSetup.remainingTries);
                hangmanDisplay.displayHangman();
                return false;
            } else {
                gameSetup.underline = newUnderline.toString(); 
                hangmanWord.setText(gameSetup.underline);
                hangmanDisplay.displayHangman();
                return true;
            }
        }
        else{
            return false;
        }
        
    }
    
    //checks to see if the game is over
    public void gameOver(){
        if(gameSetup.isGameEnded()){
            if(gameSetup.win){
                gameOverLabel.setText("Game Over: good job on the win");
                
                center.add(gameOverLabel);
            }
            else{
                hangmanWord.setText(gameSetup.getWord());
                center.add(gameOverLabel);  
            }

            
            //this.setVisible(false);
            
        }
    }
    
    public void didWin(){
        if(gameSetup.underline.replace(" ", "").equals(gameSetup.getWord())){
            System.out.println("\nWELL DONE! \nYou have guessed successfully! \nThe word was: " + gameSetup.getWord());
            gameSetup.win = true;
            gameSetup.isGameEnded();
            
        }
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameSetup.isGameEnded()){
            for(JButton btn : alphabetButtons){
                
                if(e.getSource() == btn){

                    String buttonsText = btn.getText().trim();


                    if(hang(buttonsText)){//letter guessed was right
                        btn.setBackground(Color.GREEN);
                        didWin();
                        gameOver();
                        
                    }
                    else{//letter guess was wrong
                        btn.setBackground(Color.red);
                        //lives.setText("Lives left: " + gameSetup.remainingTries);
                        gameOver();
                    }

                    System.out.println("Contains: " + buttonsText);
                }

            }
        }

        
        
    }
}
