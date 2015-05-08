package ht;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Herendi Tibor
 */
public class HtMain extends JFrame {

    JButton gameButton;
    JButton exitButton;
    JButton newGameButton;
    MenuPanel mp;
    EndGamePanel egp;
    Board bgp;

    Runnable endGameWatcher = new Runnable() {

        @Override
        public void run() {
            while (!bgp.isGameEnded());
            setupEndGameFrame();
        }
    };

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
            //System.out.println(e.getKeyCode());
            switch (e.getKeyCode()) {
                case 40:
                    mp.incChosenMenu();
                    mp.repaint();
                    break;
                case 38:
                    mp.descChosenMenu();
                    mp.repaint();
                    break;
                case 10:
                    if (mp.getChosenMenu() == 0) {
                        remove(mp);
                        setupGamePanel();
                    } else if (mp.getChosenMenu() == 1) {
                        dispose();
                    }
                    break;
            }
        }
    };
    KeyListener endGameListener = new KeyListener() {

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
            //System.out.println(e.getKeyCode());
            switch (e.getKeyCode()) {
                case 40:
                    egp.incChosenMenu();
                    egp.repaint();
                    break;
                case 38:
                    egp.descChosenMenu();
                    egp.repaint();
                    break;
                case 10:
                    if (egp.getChosenMenu() == 0) {
                        remove(egp);
                        egp.setFocusable(false);
                        setupGamePanel();
                    } else if (egp.getChosenMenu() == 1) {
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
        this.setSize(870, 890);
        this.setPreferredSize(new Dimension(870, 890));
        this.setResizable(false);
        this.setFocusable(false);
    }

    private void setupGamePanel() {
        bgp = new Board();
        bgp.setFocusable(true);
        bgp.requestFocus();
        bgp.requestFocusInWindow();
        this.add(bgp);
        bgp.updateUI();
        this.pack();
        new Thread(endGameWatcher).start();
    }

    private void setupMenuFrame() {
        mp = new MenuPanel();
        mp.setLayout(new FlowLayout());
        mp.setSize(870, 870);
        mp.setPreferredSize(new Dimension(870, 870));
        mp.setBackground(Color.black);
        mp.setFocusable(true);
        mp.requestFocus();
        mp.requestFocusInWindow();
        mp.addKeyListener(menuListener);
        this.add(mp);
        this.pack();
    }

    private void setupEndGameFrame() {
        boolean isdead = bgp.isDead();
        long gametime = bgp.gameEndedTime();
        int collected = bgp.getCollectedCoins();
        bgp.setFocusable(false);
        egp = new EndGamePanel(isdead, gametime, collected);
        egp.setLayout(new FlowLayout());
        egp.setSize(870, 870);
        egp.setPreferredSize(new Dimension(870, 870));
        egp.setBackground(Color.black);
        egp.setFocusable(true);
        egp.addKeyListener(endGameListener);
        egp.setFocusable(true);
        egp.requestFocus();
        egp.requestFocusInWindow();
        remove(bgp);
        add(egp);
        egp.updateUI();
        this.pack();

    }
}
