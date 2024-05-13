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
import java.util.Locale;

public class PreGameFrame extends JFrame implements ActionListener{

    public int WIDTH = 700;
    public int HEIGHT = 700;
    public String TITLE = "Setup Hangman Game";
    
    
    
    //buttons
    public JButton signUp;
    public JButton signIn;
    public JButton start;
    public JButton addWord;
    public JButton quit;
    
    
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
        }
        if (e.getSource() == this.signIn){
            System.out.println("Sign in button");
        }
        if (e.getSource() == this.signUp) {
            System.out.println("Sign up button");
        }
        if (e.getSource() == this.addWord){
            System.out.println("Add word button");
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
