package ht;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Herendi Tibor
 */
public class Board extends JPanel {

    private PacMan pacman;
    private Dimension d;
    Timer timer;
    int delay;
    int boardRowNumb;
    int boardColNumb;
    int speed;

    ActionListener timerListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    };
    KeyListener dirListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case 38:
                    pacman.changeDir(Direction.UP);
                    break;
                case 40:
                    pacman.changeDir(Direction.DOWN);
                    break;
                case 37:
                    pacman.changeDir(Direction.LEFT);
                    break;
                case 39:
                    pacman.changeDir(Direction.RIGHT);
                    break;
            }
        }
    };

    public Board() {
        setupPanel();
        setupVariables();
        setupImages();
        setupListeners();
    }

    private void setupPanel() {
        d = new Dimension(800, 600);
        this.setSize(d);
        this.setPreferredSize(d);
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);

    }

    private void setupImages() {
    }

    private void setupVariables() {
        this.pacman = new PacMan();
        this.boardRowNumb = (this.boardColNumb = 12);
        this.speed = 1;
    }

    private void setupListeners() {
        //Time Listener
        this.delay = 1;
        this.timer = new Timer(delay, timerListener);
        this.timer.start();
        //direction Listener
        this.addKeyListener(dirListener);
    }

    private void movePacMan() {
        switch (this.pacman.getDir()) {
            case LEFT:
                this.pacman.move((0 - 1)*speed,0);
                break;
            case RIGHT:
                this.pacman.move((0 + 1)*speed, 0);
                break;
            case UP:
                this.pacman.move(0, (0 - 1)*speed);
                break;
            case DOWN:
                this.pacman.move(0, (0 + 1)*speed);
                break;
        }
    }

    private void drawPacman(Graphics2D g) {
        g.setColor(Color.yellow);
        g.fillOval(pacman.getPosX(), pacman.getPosY(), d.width / 10, d.height / 10);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);

    }

    private void drawBoard(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, this.d.width, this.d.height);

        movePacMan();
        drawPacman(g2d);
    }

}
