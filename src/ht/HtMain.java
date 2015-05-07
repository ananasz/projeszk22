package ht;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
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
    MenuPanel p;
    Board b;

    KeyListener menuListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // 40 -> arrow_down
            // 38 -> arrow_up
            // 10 -> enter
            System.out.println(e.getKeyCode());
            switch (e.getKeyCode()) {
                case 40:
                    p.incChosenMenu();
                    p.repaint();
                    break;
                case 38:
                    p.descChosenMenu();
                    p.repaint();
                    break;
                case 10:
                    if (p.getChosenMenu() == 0) {
                        remove(p);
                        setupGamePanel();
                    } else if (p.getChosenMenu() == 1) {
                        dispose();
                    }
                    break;
            }
        }
    };

    public HtMain() {
        setupFrame();
        setupMenuFrame();
    }

    private void setupFrame() {
        this.setLayout(new GridLayout(1, 1));
        this.setTitle("Pac-Man");
        this.setLocation(0, 0);
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
        p = new MenuPanel();
        p.setLayout(new FlowLayout());
        p.setSize(870, 870);
        p.setPreferredSize(new Dimension(870, 870));
        p.setBackground(Color.black);
        p.setFocusable(true);
        p.addKeyListener(menuListener);
        this.add(p);
        this.pack();
    }
}
