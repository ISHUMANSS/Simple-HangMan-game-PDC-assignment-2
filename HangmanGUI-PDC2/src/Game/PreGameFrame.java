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

public class PreGameFrame extends JFrame implements ActionListener{

    public int WIDTH = 700;
    public int HEIGHT = 700;
    public String TITLE = "Setup Hangman Game";
    
    //UserName
    public JLabel usernameLabel;
    
    //buttons
    public JButton signUp;
    public JButton signIn;
    public JButton start;
    public JButton addWord;
    public JButton quit;
    
    
    //Set up other frames
    public AddWordGui addWordGUI;
    public UserSignUpGUI userSignUp;
    public UserLogin userLogin;
    public GUIFrame hangmanGame;
    
    
    //set up background components
    public Username username;
    public UsernameManager usernameManager;
    private boolean isLoggedIn;
    
    
    
    
    public PreGameFrame(){
        initComponents();
        initPanels();
        initActionListener();
    }
    
    
    
    public void initComponents(){
        this.signUp = new JButton("Sign Up");
        this.signIn = new JButton("Sign In");
        this.start = new JButton("Start Game");
        this.addWord = new JButton("Add word");//might seperate this into a section of admin users only
        this.quit = new JButton("Quit");
        
        
        
        
        //Init other needed classes
        this.username = new Username("Guest");
        this.usernameManager = new UsernameManager(username);
        this.isLoggedIn = false;
        
        
        this.usernameLabel = new JLabel(username.getUsername());
        
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
        
        JPanel northPanel = new JPanel();
        northPanel.add(usernameLabel);
        this.add(northPanel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 1));
        centerPanel.add(signIn);
        centerPanel.add(signUp);
        centerPanel.add(start);
        centerPanel.add(addWord);
        centerPanel.add(quit);
        
        this.add(centerPanel, BorderLayout.CENTER);
        
    }
    
    public void initActionListener(){
        this.signIn.addActionListener(this);
        this.signUp.addActionListener(this);
        this.start.addActionListener(this);
        this.addWord.addActionListener(this);
        this.quit.addActionListener(this);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        if (e.getSource() == this.start) {
            System.out.println("Game started button");
            //make game code
            hangmanGame = new GUIFrame();
            hangmanGame.setVisible(true);
            
            //this.setVisible(false);
        }
        
        if (e.getSource() == this.signIn){
            System.out.println("Sign in button");
            //Sign in code
            userLogin = new UserLogin(usernameManager);
            userLogin.setVisible(true);
            
            //this.setVisible(false);
            
            
            usernameLabel.setText(username.getUsername());
            
        }
        if (e.getSource() == this.signUp) {
            System.out.println("Sign up button");
            //Sign up code
            userSignUp = new UserSignUpGUI();
            userSignUp.setVisible(true);
            
            //this.setVisible(false);
            
        }
        if (e.getSource() == this.addWord){
            
            if(isLoggedIn){
                System.out.println("Add word button");
                //add words code
                addWordGUI = new AddWordGui();
                addWordGUI.setVisible(true);

                //this.setVisible(false);

            }
        }
        if (e.getSource() == this.quit){
            System.out.println("Quit button");
            System.exit(0);
        }
    }
    
    //USED FOR BASIC TESTING!!!
    public static void main(String[] args) {
        
        //will get rid of for a proper main class
        PreGameFrame pgf = new PreGameFrame();
        pgf.setVisible(true);
    }
    
    
}
