/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author alist
 */

//Displays the images of the man being hanged

//private Image image;
public class GraphicalHanger extends JPanel{
    //has an image for each section of when the player gets it wrong
    //graphical version of HangmanDisplay class
    
    public Icon image;
    
    public JLabel lableForImage;
    
    
    public int imageWidth;
    public int imageHeight;
    public int imagex;
    public int imagey;
    public HangmanDisplay hangmanCounter;//for the extra displaying in the CLI
    
    
    
    public GraphicalHanger(HangmanDisplay hangmanDisplay ){
        this.image = new ImageIcon(new ImageIcon("./images/HangmanImageDefult.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        
        this.lableForImage = new JLabel(image);
        
        this.add(lableForImage,BorderLayout.SOUTH);
        this.hangmanCounter = hangmanDisplay;
    }
    public GraphicalHanger(){
        this.image = new ImageIcon(new ImageIcon("./images/HangmanImageDefult.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        
        this.lableForImage = new JLabel(image);
        
        this.add(lableForImage,BorderLayout.SOUTH);
        this.hangmanCounter = new HangmanDisplay();
    }
    
    public void loadImage(){
        
//        hangmanCounter.incrementCount(); //used for testing to see if all the images work
        switch(hangmanCounter.obtainCount()){
            case 1: 
                image = new ImageIcon(new ImageIcon("./images/HangmanImage1.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                lableForImage.setIcon(image);
                hangmanCounter.displayHangman();
                break;
            case 2:
                image = new ImageIcon(new ImageIcon("./images/HangmanImage2.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                lableForImage.setIcon(image);
                hangmanCounter.displayHangman();
                break;
            case 3: 
                image = new ImageIcon(new ImageIcon("./images/HangmanImage3.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                lableForImage.setIcon(image);
                hangmanCounter.displayHangman();
                break;
            case 4:
                 image = new ImageIcon(new ImageIcon("./images/HangmanImage4.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                lableForImage.setIcon(image);
                hangmanCounter.displayHangman();
                break;
            case 5: 
                image = new ImageIcon(new ImageIcon("./images/HangmanImage5.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                lableForImage.setIcon(image);
                hangmanCounter.displayHangman();
                break;
            case 6:
                image = new ImageIcon(new ImageIcon("./images/HangmanImage6.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                lableForImage.setIcon(image);
                hangmanCounter.displayHangman();
                break;
            case 7: 
                image = new ImageIcon(new ImageIcon("./images/HangmanImage7.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                lableForImage.setIcon(image);
                hangmanCounter.displayHangman();
                break;
            case 8:
                image = new ImageIcon(new ImageIcon("./images/HangmanImage8.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                lableForImage.setIcon(image);
                hangmanCounter.displayHangman();
                //hangmanCounter.makeCount0();
                break;
            default:
                case 0:
                image = new ImageIcon(new ImageIcon("./images/HangmanImageDefult.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                lableForImage.setIcon(image);
                break;
        }
        
    }
    
    
    @Override
    public void paintComponent( Graphics g ){
        super.paintComponent(g);
        
        loadImage();
        repaint();
    }
    
}
