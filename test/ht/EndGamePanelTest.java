/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Herendi Tibor
 */
public class EndGamePanelTest {
    
    EndGamePanel egp;
    
    @Test
    public void testIncChosenMenu() {
        egp = new EndGamePanel(true,0,0,0);
        
        System.out.println("incChosenMenu testing #1: chosenMenu value must be 1 after calling incChosenMenu");
        int expres = 1;
        egp.descChosenMenu();
        int result = egp.getChosenMenu();
        assertEquals(expres, result);
        
        System.out.println("incChosenMenu testing #2: chosenMenu value must be 0 after calling incChosenMenu");
        expres = 0;
        egp.descChosenMenu();
        result = egp.getChosenMenu();
        assertEquals(expres, result);
    }

    @Test
    public void testDescChosenMenu() {
        egp = new EndGamePanel(true,0,0,0);
        
        System.out.println("descChosenMenu testing #1: chosenMenu value must be 1 after calling descChosenMenu");
        int expres = 1;
        egp.descChosenMenu();
        int result = egp.getChosenMenu();
        assertEquals(expres, result);
        
        System.out.println("descChosenMenu testing #2: chosenMenu value must be 0 after calling descChosenMenu");
        expres = 0;
        egp.descChosenMenu();
        result = egp.getChosenMenu();
        assertEquals(expres, result);
    }
    
    @Test
    public void testEndGameWon(){
        egp = new EndGamePanel(false,0,60,60);
        if(egp.isDead() == false && egp.getBoardCoins() != egp.getCollectedCoins()){
            fail("The game was won and the number of collected coins didnt't match with the number of coins on the field");
        }
    }
}
