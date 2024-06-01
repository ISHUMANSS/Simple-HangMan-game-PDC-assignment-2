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
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Frame that sets up the game
public class GUIFrame extends JFrame implements ActionListener{
   
    
    public int WIDTH = 700;
    public int HEIGHT = 700;
    public String TITLE = "Hangman Game";
    
    
    
    //Labels
    public JLabel hangmanWord;
    public JLabel lives;
    
    //Fonts
    public Font hangmanWordFont;
    public Font livesFont;
    
    //buttons
    public JButton testButton; //currenly starts the game to get rid of soon
    private static final String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R","S", "T", "U", "V", "W", "X", "Y", "Z" };
    public JButton [] alphabetButtons = new JButton[letters.length];
    
    
    public HangmanGame gameRunner ;
    public HangmanDisplay hangmanDisplay;
    
    
    
    //constructor that makes all the components work
    public GUIFrame(){
        initComponents();
        initPanels();
        initActionListener();
    }
    
    public void initComponents(){
        //set up the fonts
        this.hangmanWordFont = new Font("Calibri", Font.BOLD, 30);
        this.livesFont = new Font("Calibri", Font.PLAIN, 20);
        
        //sets up all the components
        this.testButton = new JButton("Gamestart");
        this.hangmanWord = new JLabel("HangMan word", SwingConstants.CENTER);
        this.hangmanWord.setFont(hangmanWordFont);
        this.lives = new JLabel("Lives left: 8", SwingConstants.CENTER);//how many lifes left
        this.lives.setFont(livesFont);
        
        
        //set up alphabet buttons
        for(int i = 0; i < letters.length; ++i){
            this.alphabetButtons[i] = new JButton(letters[i]);
            
            //was adding action listeners to the buttons twice
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
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,1));
        northPanel.add(hangmanWord);
        northPanel.add(lives);
        
        this.add(northPanel, BorderLayout.NORTH);
        
        
        //south Panel
        JPanel southPanel = new JPanel();
        southPanel.add(testButton);
        
        this.add(southPanel, BorderLayout.SOUTH);
        
        //Center Pannel
        //Buttons part of center
        JPanel holder = new JPanel();
        holder.setLayout(new GridLayout(2,1));
        JPanel alphaButtonsPannel = new JPanel();
        alphaButtonsPannel.setLayout(new GridLayout(2, 13));
        for(int i = 0; i < alphabetButtons.length; i++){
            alphaButtonsPannel.add(alphabetButtons[i]);
        }
        //image part of center
        GraphicalHanger gh = new GraphicalHanger(hangmanDisplay);
        holder.add(gh, BorderLayout.NORTH);
        holder.add(alphaButtonsPannel);
        
        this.add(holder, BorderLayout.CENTER);
        
        
    }
    
    public void initActionListener(){
        //assigns all the action listeners
        this.testButton.addActionListener(this);
        
        for (JButton alphabetButton : alphabetButtons) {
            alphabetButton.addActionListener(this);
        }
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.testButton) {
            WordRandomiser wr = new WordRandomiser();
            System.out.println("Random word is: " + wr.randomWord);
            this.hangmanWord.setText(wr.randomWord);
           
            //added for testing
            HangmanDisplay hd = new HangmanDisplay();
            hd.displayHangman();
            hd.incrementCount();
            hd.displayHangman();
        }
        for(JButton btn : alphabetButtons){
            if(e.getSource() == btn){
                
                String buttonsText = btn.getText().trim();

                //if the letter hasn't alrealdy been guessed
//                if(){
//                    
//                }
//                else{//letter has been guessed
//                    btn.setBackground(Color.red);
//                }
                
                System.out.println("Contains: " + buttonsText);
            }
        }
//        old button code
//        if(e.getSource() == this.alphabetButtons){
//            
//            
//            JButton pressedButton = (JButton) e.getSource();
//            
//            String buttonsName = pressedButton.getName();//Currently shows null :(
//            String buttonsText = pressedButton.getText().trim();
//            
//            if(buttonsText.equals(Character.isAlphabetic(0)) ){
//                //what the button does
//                
//            }
//            
//            System.out.println(buttonsName + " was pressed, contains: " + buttonsText);
//        }
        
        
        
    }
}
