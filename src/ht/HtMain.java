package ht;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Herendi Tibor
 */
public class HtMain extends JFrame {

    JButton gameButton;
    JButton exitButton;
    JButton newGameButton;
    JPanel p;
    Board b;

    ActionListener gameListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            remove(p);
            setupGamePanel();
        }
    };
    ActionListener exitListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            removeAll();
            dispose();
        }
    };

    public HtMain() {
        setupFrame();
        setupMenuFrame();
    }

    private void setupFrame() {
        this.setLayout(new FlowLayout());
        this.setTitle("Pac-Man");
        this.setLocation(0,0);
        this.setSize(870, 870);
        this.setPreferredSize(new Dimension(870, 870));
        this.setResizable(false);
    }

    private void setupGamePanel() {
        b = new Board();
        b.setFocusable(true);
        this.add(b);
        b.updateUI();
        this.pack();
    }

    private void setupMenuFrame() {
        p = new JPanel();
        p.setSize(870, 870);
        p.setPreferredSize(new Dimension(870, 870));
        p.setBackground(Color.black);

        gameButton = new JButton();
        gameButton.setSize(600, 500);
        gameButton.setLocation(600, 400);
        gameButton.setText("GAEM");
        gameButton.setBackground(Color.green);
        gameButton.addActionListener(gameListener);
        p.add(gameButton);

        exitButton = new JButton();
        exitButton = new JButton();
        exitButton.setSize(600, 500);
        exitButton.setLocation(600, 600);
        exitButton.setText("EXIT");
        exitButton.setBackground(Color.green);
        exitButton.addActionListener(exitListener);
        p.add(exitButton);

        this.add(p);
        this.pack();
    }
}
