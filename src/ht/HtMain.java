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
        setupPanel();
    }
    
    private void setupFrame(){
        this.setLayout(new FlowLayout());
        this.setTitle("Pac-Man");
        this.setSize(new Dimension(800, 600));
        this.setPreferredSize(new Dimension(800, 600));
        this.setLocation(this.getSize().width/2,this.getSize().height/2);
    }
    
    private void setupPanel(){
        Board b = new Board();
        this.add(b);
        this.pack();
    }
}
