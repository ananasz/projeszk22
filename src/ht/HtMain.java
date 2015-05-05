package ht;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author Herendi Tibor
 */
public class HtMain extends JFrame {
    public HtMain(){
        setupFrame();
        setupBoardPanel();
    }
    
    private void setupFrame(){
        this.setLayout(new FlowLayout());
        this.setTitle("Pac-Man");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    
    private void setupBoardPanel(){
        Board b = new Board();
        this.add(b);
        this.pack();
    }
}
