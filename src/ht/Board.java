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
    Timer timer;
    int delay;

    int boardRowNumb, boardColNumb;
    int blockWidth, blockHeight, blockRad;
    int speed, maxspeed, minspeed;

    int collectedPoints, boardPoints;

    ArrayList<Pos> blocks;
    int[] coins;

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
        this.collectedPoints = 0;
        this.boardPoints = 30;
        this.maxspeed = 4;
        this.minspeed = 1;

        blocks = new ArrayList<>();
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
    }

    private void movePacMan() {
        int diff = 5;
        switch (this.pacman.getDir()) {
            case LEFT:
                if (!(checkCollison((pacman.getPosX() + -1 * speed - blockRad), pacman.getPosY()))) {
                    return;
                }
                if (!(checkCollison((pacman.getPosX() + -1 * speed - blockRad), pacman.getPosY() + blockRad -diff))) {
                    return;
                }
                if (!(checkCollison((pacman.getPosX() + -1 * speed - blockRad), pacman.getPosY() - blockRad + diff))) {
                    return;
                }
                this.pacman.move(-1 * speed, 0);
                break;

            case RIGHT:
                if (!(checkCollison((pacman.getPosX() + 1 * speed + blockRad), pacman.getPosY()))) {
                    return;
                }
                if (!(checkCollison((pacman.getPosX() + 1 * speed + blockRad), pacman.getPosY() + blockRad - diff))) {
                    return;
                }
                if (!(checkCollison((pacman.getPosX() + 1 * speed + blockRad), pacman.getPosY() - blockRad + diff))) {
                    return;
                }
                this.pacman.move(1 * speed, 0);
                break;

            case UP:
                if (!(checkCollison((pacman.getPosX()), pacman.getPosY() + -1 * speed - blockRad))) {
                    return;
                }
                if (!(checkCollison((pacman.getPosX() + blockRad - diff), pacman.getPosY() + -1 * speed - blockRad))) {
                    return;
                }
                if (!(checkCollison((pacman.getPosX() - blockRad + diff), pacman.getPosY() + -1 * speed - blockRad))) {
                    return;
                }
                this.pacman.move(0, -1 * speed);
                break;

            case DOWN:
                for (int i = 0; i <= 1; i++) {
                    if (!(checkCollison(pacman.getPosX(), pacman.getPosY() + 1 * speed + blockRad * i))) {
                        return;
                    }
                }
                if (!(checkCollison((pacman.getPosX()), pacman.getPosY() + 1 * speed + blockRad))) {
                    return;
                }
                if (!(checkCollison((pacman.getPosX() + blockRad - diff), pacman.getPosY() + 1 * speed + blockRad))) {
                    return;
                }
                if (!(checkCollison((pacman.getPosX() - blockRad + diff), pacman.getPosY() + 1 * speed + blockRad))) {
                    return;
                }
                this.pacman.move(0, 1 * speed);
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
        for (Pos p : blocks) {
            g2d.setColor(Color.green);
            g2d.fillRect(p.x, p.y, this.blockHeight, this.blockWidth);
        }
    }

    private boolean checkCollison(int x, int y) {
        //falakkal való ütküzésnek ellenörzése
        //körlapon lévő P(px;py) pont esetén, ha (px-x0)^2+(py-y0)^2 < r2, akkor körlapon van
        //minden blokkra leellenörzöm, hogy benne van e. ha igen, akkor nem mehet tovább, iránytól függően

        for (Pos p : blocks) {
            if ((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y) < (this.blockRad) * (this.blockRad)) {
                return false;
            }
        }

        return true;
    }

    private void setSpeed(int m) {
        if (!(this.speed + m > this.maxspeed || this.speed + m < this.minspeed)) {
            this.speed += m;
        }
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
