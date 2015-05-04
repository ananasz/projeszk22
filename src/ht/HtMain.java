package ht;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
        this.setTitle("Pac-Man");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(640, 480));
        this.setLocation(this.getSize().width/2,this.getSize().height/2);
    }
    
    private void setupPanel(){
        JPanel panel = new JPanel();
        this.add(panel);
    }
}
