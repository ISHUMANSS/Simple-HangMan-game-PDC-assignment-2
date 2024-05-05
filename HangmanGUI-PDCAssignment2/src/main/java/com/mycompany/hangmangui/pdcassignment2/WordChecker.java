/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hangmangui.pdcassignment2;

/**
 *
 * @author alist
 */

import java.util.HashMap;
public class WordChecker extends WordRandomiser{
    
    //takes in the random word and the guess
    //keeps the index of each character that matches 
    //uses a hash map 
    //goes through the string and compares each letter of it to the letter of the guess
    public void wordCheck(Character guess){
        HashMap<Integer, Character> hm = new HashMap();
        
        
        //use map to keep the location and character
        //put underScores for every element thats not the guesses character in the map
        for(int i = 0; i < randomWord.length(); i++){
            if(guess.equals(randomWord.charAt(i))){
                hm.put(i, guess);
            }
            else{
                hm.put(i, '_');
            }
        }
        
        System.out.println(hm);
    
        
            
    }
    
}