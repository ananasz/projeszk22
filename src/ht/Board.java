package ht;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Herendi Tibor
 */
class Pos {

    public int x, y;
}

public class Board extends JPanel {

    private PacMan pacman;
    private ArrayList<Movable> ghosts;
    
    private Dimension d;
    Timer timer;
    int delay;
    
    int boardRowNumb,boardColNumb;
    int blockWidth,blockHeight;
    int speed,maxspeed,minspeed;

    int collectedPoints,boardPoints;

    ArrayList<Pos> blocks,coins;

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
            //System.out.println(e.getKeyCode());
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
                case 107:
                    setSpeed(1);
                    break;
                case 109:
                    setSpeed(-1);
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
        this.pacman = new PacMan(this.blockWidth / 2, this.blockHeight / 2);
        this.boardRowNumb = (this.boardColNumb = 12);
        this.speed = 1;
        this.collectedPoints = 0;
        this.boardPoints = 30;
        this.maxspeed = 4;
        this.minspeed = 1;
    }

    private void setupListeners() {
        //Time Listener
        this.delay = 10;
        this.timer = new Timer(delay, timerListener);
        this.timer.start();
        //direction Listener
        this.addKeyListener(dirListener);
    }

    private void movePacMan() {
        switch (this.pacman.getDir()) {
            case LEFT:
                if (checkCollide()) {
                    this.pacman.move((0 - 1) * speed, 0);
                }
                break;
            case RIGHT:
                if (checkCollide()) {
                    this.pacman.move((0 + 1) * speed, 0);
                }
                break;
            case UP:
                if (checkCollide()) {
                    this.pacman.move(0, (0 - 1) * speed);
                }
                break;
            case DOWN:
                if (checkCollide()) {
                    this.pacman.move(0, (0 + 1) * speed);
                }
                break;
        }
    }

    private void drawPacman(Graphics2D g) {
        g.drawImage(this.pacman.getImage(), pacman.getPosX(), pacman.getPosY(), this);
    }

    private void drawStatus(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawString("COLLECTED POINTS: " + this.collectedPoints, 10, 10);
        g2d.drawString("SPEED: " + this.speed, 10, 20);
    }

    private void drawMaze(Graphics2D g2d) {
        //páya feltöltése fekete színnel
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, this.d.width, this.d.height);

        //pálya szélének rajzolása
        g2d.setColor(Color.white);
        g2d.fillRect(this.blockWidth, this.blockHeight, this.d.width, this.blockHeight);

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, this.d.width, this.d.height);

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, this.d.width, this.d.height);

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, this.d.width, this.d.height);

        //labirintus kövek rajzolása
        //gyüjthető pontok rajzolása
    }

    private boolean checkCollide() {
        return true;
    }
    
    private void setSpeed(int m){
        if(!(this.speed + m > this.maxspeed || this.speed + m < this.minspeed)) this.speed+=m;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);

    }

    private void drawBoard(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        movePacMan();
        drawMaze(g2d);
        drawPacman(g2d);
        drawStatus(g2d);
    }

}
