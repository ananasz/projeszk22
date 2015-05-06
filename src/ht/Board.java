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
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Herendi Tibor
 */
class Pos {

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int x, y;
}

public class Board extends JPanel {

    private PacMan pacman;
    private ArrayList<Movable> ghosts;

    private Dimension d;
    private Timer timer;
    private int delay;

    private int boardRowNumb, boardColNumb;
    private int blockWidth, blockHeight, blockRad;
    private int speed, maxspeed, minspeed;

    private int collectedCoins, boardCoins;

    private long gameStartTime;

    ArrayList<Pos> blocks;
    ArrayList<Pos> coins;

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
        d = new Dimension(870, 870);
        this.setSize(d);
        this.setPreferredSize(d);
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);

    }

    private void setupImages() {
    }

    private void setupVariables() {
        this.blockWidth = (this.blockHeight = 58);
        this.blockRad = blockWidth / 2;
        this.pacman = new PacMan(this.getWidth() / 2, this.getHeight() / 2);
        this.boardRowNumb = (this.boardColNumb = d.height / this.blockWidth);
        this.speed = 1;
        this.collectedCoins = 0;
        this.boardCoins = 60;
        this.maxspeed = 4;
        this.minspeed = 1;
        gameStartTime = System.currentTimeMillis();
        blocks = new ArrayList<>();
        coins = new ArrayList<>();
        fillMaze();
    }

    private void setupListeners() {
        //Time Listener
        this.delay = 10;
        this.timer = new Timer(delay, timerListener);
        this.timer.start();
        //direction Listener
        this.addKeyListener(dirListener);
    }

    private void fillMaze() {
        //blocks
        for (int i = 0; i < boardRowNumb * 2; ++i) {
            blocks.add(new Pos(0, blockWidth * i / 2));
            blocks.add(new Pos(blockWidth * i / 2, 0));
            blocks.add(new Pos(blockWidth * i / 2, boardRowNumb * blockWidth - blockWidth));
            blocks.add(new Pos(boardRowNumb * blockWidth - blockWidth, blockWidth * i / 2));
        }

        for (int i = 4; i < boardRowNumb * 2 - 5; ++i) {
            blocks.add(new Pos(blockWidth * 2, blockWidth * i / 2));
            blocks.add(new Pos(blockWidth * 2 + blockWidth / 2, blockWidth * i / 2));
        }

        int i = 0;
        while (i < boardCoins) {
            Pos p = new Pos((int) (new Random().nextInt(870)), (int) (new Random().nextInt(870)));
            if ((blockCollison(p.x, p.y))
                    && (blockCollison(p.x - blockRad, p.y))
                    && (blockCollison(p.x + blockRad, p.y))
                    && (blockCollison(p.x, p.y - blockRad))
                    && (blockCollison(p.x, p.y + blockRad))) {
                this.coins.add(p);
                ++i;
            }
        }

    }

    private void movePacMan() {
        coinCollison(this.pacman.getPos().x, this.pacman.getPos().y);
        ghostCollison();
        
        int diff = 5;
        switch (this.pacman.getDir()) {
            case LEFT:
                if (!(blockCollison((pacman.getPos().x + -1 * speed - blockRad), pacman.getPos().y))) {
                    return;
                }
                if (!(blockCollison((pacman.getPos().x + -1 * speed - blockRad), pacman.getPos().y + blockRad - diff))) {
                    return;
                }
                if (!(blockCollison((pacman.getPos().x + -1 * speed - blockRad), pacman.getPos().y - blockRad + diff))) {
                    return;
                }
                this.pacman.move(-1 * speed, 0);
                break;

            case RIGHT:
                if (!(blockCollison((pacman.getPos().x + 1 * speed + blockRad), pacman.getPos().y))) {
                    return;
                }
                if (!(blockCollison((pacman.getPos().x + 1 * speed + blockRad), pacman.getPos().y + blockRad - diff))) {
                    return;
                }
                if (!(blockCollison((pacman.getPos().x + 1 * speed + blockRad), pacman.getPos().y - blockRad + diff))) {
                    return;
                }
                this.pacman.move(1 * speed, 0);
                break;

            case UP:
                if (!(blockCollison((pacman.getPos()).x, pacman.getPos().y + -1 * speed - blockRad))) {
                    return;
                }
                if (!(blockCollison((pacman.getPos().x + blockRad - diff), pacman.getPos().y + -1 * speed - blockRad))) {
                    return;
                }
                if (!(blockCollison((pacman.getPos().x - blockRad + diff), pacman.getPos().y + -1 * speed - blockRad))) {
                    return;
                }
                this.pacman.move(0, -1 * speed);
                break;

            case DOWN:
                if (!(blockCollison(pacman.getPos().x, pacman.getPos().y + 1 * speed + blockRad))) {
                    return;
                }
                if (!(blockCollison((pacman.getPos().x + blockRad - diff), pacman.getPos().y + 1 * speed + blockRad))) {
                    return;
                }
                if (!(blockCollison((pacman.getPos().x - blockRad + diff), pacman.getPos().y + 1 * speed + blockRad))) {
                    return;
                }
                this.pacman.move(0, 1 * speed);
                break;
        }
    }

    private void drawPacman(Graphics2D g) {
        g.drawImage(this.pacman.getImage(), pacman.getPos().x, pacman.getPos().y, this);
    }

    private void drawStatus(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawString("COLLECTED POINTS: " + this.collectedCoins, 10, 10);
        g2d.drawString("SPEED: " + this.speed, 10, 20);
        g2d.drawString("ELAPSED TIME: " + (System.currentTimeMillis() - this.gameStartTime) / 1000, 10, 30);
    }

    private void drawMaze(Graphics2D g2d) {
        //páya feltöltése fekete színnel
        for (Pos p : blocks) {
            g2d.setColor(Color.green);
            g2d.fillRect(p.x, p.y, this.blockHeight, this.blockWidth);
        }
        for (Pos p : coins) {
            g2d.setColor(Color.yellow);
            g2d.fillOval(p.x, p.y, 6, 6);
        }
    }

    private boolean checkIsNotCollided(Pos p, Pos q, int rad) {

        if ((p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y) < (rad) * (rad)) {
            return false;
        }

        return true;
    }

    private void setSpeed(int m) {
        if (!(this.speed + m > this.maxspeed || this.speed + m < this.minspeed)) {
            this.speed += m;
        }
    }

    private void coinCollison(int x, int y) {

        for (Pos p : coins) {
            if (!(checkIsNotCollided(p, new Pos(x + this.blockRad, y + this.blockRad), this.blockRad + 5))) {
                coins.remove(p);
                this.collectedCoins++;
                break;
            }
        }
    }

    private void ghostCollison() {

    }

    private boolean blockCollison(int x, int y) {
        for (Pos p : blocks) {
            if (!checkIsNotCollided(new Pos(x, y), p, this.blockRad)) {
                return false;
            }
        }
        return true;
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
