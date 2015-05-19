package ht;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Herendi Tibor
 */
public class MenuPanelTest {
    
    MenuPanel mp;
    
    @Before
    public void setMenuPanel(){
        mp = new MenuPanel();
    }
    
    @Test
    public void testIncChosenMenu() {
        System.out.println("incChosenMenu testing #1: chosenMenu value must be 1 after calling incChosenMenu");
        int expres = 1;
        mp.descChosenMenu();
        int result = mp.getChosenMenu();
        assertEquals(expres, result);
        
        System.out.println("incChosenMenu testing #2: chosenMenu value must be 0 after calling incChosenMenu");
        expres = 0;
        mp.descChosenMenu();
        result = mp.getChosenMenu();
        assertEquals(expres, result);
    }

    @Test
    public void testDescChosenMenu() {
        System.out.println("descChosenMenu testing #1: chosenMenu value must be 1 after calling descChosenMenu");
        int expres = 1;
        mp.descChosenMenu();
        int result = mp.getChosenMenu();
        assertEquals(expres, result);
        
        System.out.println("descChosenMenu testing #2: chosenMenu value must be 0 after calling descChosenMenu");
        expres = 0;
        mp.descChosenMenu();
        result = mp.getChosenMenu();
        assertEquals(expres, result);
    }    
}
