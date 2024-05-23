/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

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
    
    public Image image;
    
    
    public int imageWidth;
    public int imageHeight;
    public int imagex;
    public int imagey;
    public HangmanDisplay hangmanCounter;//for the extra displaying in the CLI
    
    public GraphicalHanger(HangmanDisplay hangmanDisplay ){
        this.image = new ImageIcon("./images/HangmanImageDefult.png").getImage();
        this.hangmanCounter = hangmanDisplay;
    }
    public GraphicalHanger(){
        this.image = new ImageIcon("./images/HangmanImageDefult.png").getImage();
        this.hangmanCounter = new HangmanDisplay();
    }
    
    
    
    
    @Override
    public void paintComponent( Graphics g ){
        super.paintComponent(g);
        
        imageWidth = this.getWidth() /2;
        imageHeight = this.getHeight() ;
        imagex = this.getWidth()/2 - image.getWidth(null)/2;
        imagey = this.getHeight() - image.getHeight(null) + 53;
//        hangmanCounter.incrementCount();
//        switch(hangmanCounter.obtainCount()){
//            case 1: 
//                image = new ImageIcon("./images/HangmanImage1").getImage();
//                break;
//            case 2:
//                image = new ImageIcon(".images/HangmanImage2").getImage();
//                break;
//            case 3: 
//                image = new ImageIcon("./images/HangmanImage3").getImage();
//                break;
//            case 4:
//                image = new ImageIcon(".images/HangmanImage4").getImage();
//                break;
//            case 5: 
//                image = new ImageIcon("./images/HangmanImage5").getImage();
//                break;
//            case 6:
//                image = new ImageIcon(".images/HangmanImage6").getImage();
//                break;
//            case 7: 
//                image = new ImageIcon("./images/HangmanImage7").getImage();
//                break;
//            case 8:
//                image = new ImageIcon(".images/HangmanImage8").getImage();
//                break;
//            default:
//                case 0:
//                image = new ImageIcon("./images/HangmanImageDefult").getImage();
//                break;
//        }
        
        
        g.drawImage(this.image, imagex, imagey, imageWidth, imageHeight, null);
    }
    
}
