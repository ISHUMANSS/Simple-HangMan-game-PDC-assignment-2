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
import java.awt.Dimension;
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
    
    //buttons
    public JButton testButton;
    public JLabel testText;
    
    public JLabel currentTrys;
    
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
        //sets up all the components
        this.testButton = new JButton("Gamestart");
        this.testText = new JLabel("Test text");
        this.currentTrys = new JLabel("Lives left: 8");//how many lifes left
        this.currentTrys.setSize(60, 50);
        
        //set up alphabet buttons
        for(int i = 0; i < letters.length; ++i){
            this.alphabetButtons[i] = new JButton(letters[i]);
            this.alphabetButtons[i].addActionListener(this);
            
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
        northPanel.add(testText);
        this.add(northPanel, BorderLayout.NORTH);
        
        
        //south Panel
        JPanel southPanel = new JPanel();
        southPanel.add(testButton);
        this.add(southPanel, BorderLayout.SOUTH);
        
        //button section
        JPanel holder = new JPanel();
        holder.setLayout(new GridLayout(2,1));
        JPanel alphaButtonsPannel = new JPanel();
        alphaButtonsPannel.setLayout(new GridLayout(2, 13));
        for(int i = 0; i < alphabetButtons.length; i++){
            alphaButtonsPannel.add(alphabetButtons[i]);
        }
        
        //loads the hangman image
        GraphicalHanger gh = new GraphicalHanger();
        gh.add(this.currentTrys, BorderLayout.NORTH);
        holder.add(gh, BorderLayout.SOUTH);
        holder.add(alphaButtonsPannel);
        
        this.add(holder, BorderLayout.CENTER);
        
        
    }
    
    public void initActionListener(){
        //assigns all the action listeners
        this.testButton.addActionListener(this);
        
        for(int i = 0; i < alphabetButtons.length; i++ ){
            this.alphabetButtons[i].addActionListener(this);
        }
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.testButton) {
            WordRandomiser wr = new WordRandomiser();
            System.out.println("Random word is: " + wr.randomWord);
            this.testText.setText(wr.randomWord);
            
            //added for testing
            HangmanDisplay hd = new HangmanDisplay();
            hd.displayHangman();
            hd.incrementCount();
            hd.displayHangman();
        }
        if(e.getSource() instanceof JButton){
            JButton pressedButton = (JButton) e.getSource();
            
            String buttonsName = pressedButton.getName();//Currently shows null :(
            String buttonsText = pressedButton.getText().trim();
            
            if(buttonsText.equals(Character.isAlphabetic(0)) ){
                //what the button does
                
            }
            
            //Prints out twice?
            System.out.println(buttonsName + " was pressed, contains: " + buttonsText);
        }
        
        
        
    }
}
