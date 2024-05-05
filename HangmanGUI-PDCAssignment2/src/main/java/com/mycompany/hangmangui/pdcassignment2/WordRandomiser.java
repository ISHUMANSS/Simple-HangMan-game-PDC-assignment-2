package com.mycompany.hangmangui.pdcassignment2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author alist
 */

//Takes in all the words from the file and picks a random one
public class WordRandomiser extends ReadFile { 
    String randomWord;
    
// randomly picks a word from the hash set and uses that as the word for the game
    public WordRandomiser(){
        super();
        this.randomWord = picRandom();
        
    }
    
    
    public String picRandom(){
        int size = this.wordList.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for(Object obj : wordList){
            if(i == item){
                return (String) obj;
            }
            i++;
        }
        return null;
    }
}
