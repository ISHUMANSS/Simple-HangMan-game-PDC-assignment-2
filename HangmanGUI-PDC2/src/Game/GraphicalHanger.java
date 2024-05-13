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
    
    public GraphicalHanger(){
        this.image = new ImageIcon("./images/HangmanTestimage.png").getImage();
    }
    
    @Override
    public void paintComponent( Graphics g ){
        super.paintComponent(g);
        g.drawImage(this.image, 0,0,null);
    }
    
}
