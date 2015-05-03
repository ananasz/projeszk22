/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kg;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gergely
 */
public class ColorGameLogicTest {
    
    public ColorGameLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of newGame method, of class ColorGameLogic.
     */
    @Test
    public void testNewGame() {
        boolean success = true;
        System.out.println("newGame");
        ColorGameLogic instance = new ColorGameLogic();
        instance.newGame();
        for (int i = 0; i < instance.getHeight(); i++) {
            for (int j = 0; j < instance.getWidth(); j++) {
                if (instance.getCellColor(i, j) < 0 || instance.getCellColor(i, j) > 2) {
                    success = false;
                }
            }
        }
        if (!success) {
            fail("The test case is a prototype.");
        }        
    }

    /**
     * Test of setCellColor method, of class ColorGameLogic.
     */
    @Test
    public void testSetCellColor() {
        int[] oldColors = {-1,-1,-1};
        System.out.println("setCellColor");
        ColorGameLogic instance = new ColorGameLogic();
        oldColors[0] = instance.getCellColor(1, 1);
        oldColors[1] = instance.getCellColor(1, 2);
        oldColors[2] = instance.getCellColor(2, 1);
        instance.setCellColor(1, 1);
        try {
        assertFalse(! (instance.getCellColor(1, 1) == ++oldColors[0] % 3 &&
                       instance.getCellColor(1, 2) == ++oldColors[1] % 3 &&
                       instance.getCellColor(2, 1) == ++oldColors[2] % 3) );
        } catch (AssertionError ae) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getCellColor method, of class ColorGameLogic.
     */
    @Test
    public void testGetCellColor() {
        System.out.println("getCellColor");
        ColorGameLogic instance = new ColorGameLogic();
        int result = instance.getCellColor(1, 1);
        try {
            assertFalse(result < 0 || result > 2);
        } catch (AssertionError ae) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of getHeight method, of class ColorGameLogic.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        ColorGameLogic instance = new ColorGameLogic();
        int expResult = 3;
        int result = instance.getHeight();
        try {
            assertEquals(expResult, result);
        } catch (AssertionError ae) {
            fail("The test case is a prototype.");
        }        
    }

    /**
     * Test of getWidth method, of class ColorGameLogic.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        ColorGameLogic instance = new ColorGameLogic();
        int expResult = 3;
        int result = instance.getWidth();
        try {
            assertEquals(expResult, result);
        } catch (AssertionError ae) {
            fail("The test case is a prototype.");
        }
    }
    
}
