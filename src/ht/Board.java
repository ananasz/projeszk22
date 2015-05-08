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

    /**
     * Ez egy belső osztály, a board belső működéséhez kell, más osztály számára
     * nem fontos, ezért csak belső osztály, egy pontot reprezentál.
     *
     * @param x a reprezentált pont x tengeleny lévő helye
     * @param y a reprezentált pont y tengelyen lévő helye
     */
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int x, y;
}

public class Board extends JPanel {

    /**
     * Ezek az attribútumok , melyek a board belső működéséhez szükségesek
     *
     * @param pacman a PaMan-t reprezentáló attribútum
     * @param ghosts a Ghost-ot reprezentáló objektumok egy dinamikusan bővülő
     * tömbje ez aza ttribútum
     *
     * @param d a képernyő szélességét és magasságát reprezentáló attribútum
     * @param timer timer listener attribútum, mely időközönként újrarajzolja a
     * képet
     * @param delay az újrarajzoláshoz megadott időt adja meg ez az attribútum
     *
     * @param boardRowNumb a négyzetekből álló pálya sorainak számát adja meg
     * @param boardColNumb a négyzetekből álló pálya oszlopainak számát adja meg
     * @param blockWidth a négyzetek szélessége
     * @param blockHeight a négyzetek magassága
     * @param blockRad a négyzetek belülre írható körének sugara
     * @param speed ez az attribútum adja meg, hogy milyen gyorsan haladjon a
     * pacman
     * @param maxspeed lehetséges maximum sebesség
     * @param minspeed lehetséges minimum sebesség
     *
     * @param collectedCoins ez az attribútum adja meg, hogy mennyi coinon
     * @param boardCoins a pályán található összes coin száma
     *
     * @param gameStartTime a játékban eltelt időhöz szükséges attribútum
     *
     * @param blocks a pályán található blokkokat megadó attribútumok
     * @param coins a pályán található coinokat tartalmazó tömbök
     */
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
    private ArrayList<Pos> blocks, coins;
    private volatile boolean gameEnded;

    /**
     * timeListener egy névtelen osztályként valósít meg egy interfészt, amelyet
     * egy Timer típusú objektum konstruktorában adunk meg, amely időközönként
     * az ebben az osztályban megvalósított actionPerformed() metódsut hívja meg
     * amel ebben az esetben a kép újrarajzolásáért felel
     */
    ActionListener timerListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    };
    /**
     * dirListener egy KeyListener típusú interfészt valósít meg névtelen
     * osztályként, amely a gombok lenyomásáért felelős a 38-as bill kód:
     * arrow_up, mely pacman irányát felfelé állítja a 38-as bill kód:
     * arrow_down, mely pacman irányát lefelé állítja a 38-as bill kód:
     * arrow_left, mely pacman irányát balra állítja a 38-as bill kód:
     * arrow_right, mely pacman irányát jobbra állítja a 38-as bill kód:
     * numpad_plus, pacman sebességét állítja a 38-as bill kód: numpad_minus,
     * pacman sebességét állítja
     */

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
        setupListeners();
    }

    /**
     * setupPanel() metódus állítja be az alap panel beállításokat
     */
    private void setupPanel() {
        d = new Dimension(870, 870);
        this.setSize(d);
        this.setPreferredSize(d);
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);

    }

    /**
     * setupVariables metódus állítja be a kezdetleges értékeket az
     * attribútomoknak, illetve hoozza létre az objektumoakt
     */
    private void setupVariables() {
        this.blockWidth = (this.blockHeight = 58);
        this.blockRad = this.blockWidth / 2;
        this.boardRowNumb = (this.boardColNumb = this.d.height / this.blockWidth);
        this.speed = 1;
        this.collectedCoins = 0;
        this.boardCoins = 60;
        this.maxspeed = 4;
        this.minspeed = 1;
        this.gameStartTime = System.currentTimeMillis();
        this.blocks = new ArrayList<>();
        this.coins = new ArrayList<>();
        this.gameEnded = false;
        //Movable objektumok
        this.pacman = new PacMan(this.blockWidth, this.blockHeight);
        this.ghosts = new ArrayList<>();
        this.ghosts.add(new Ghost(1 * blockWidth, (boardColNumb - 2) * blockHeight, GhostColor.RED));
        this.ghosts.add(new Ghost(3 * blockWidth, (boardColNumb - 2) * blockHeight, GhostColor.PINK));
        this.ghosts.add(new Ghost(3 * blockWidth, (boardColNumb - 2) * blockHeight, GhostColor.ORANGE));
        this.ghosts.add(new Ghost(3 * blockWidth, (boardColNumb - 2) * blockHeight, GhostColor.CYAN));
        fillMaze();
    }

    /**
     * setupListener metódus állítja be összes listenert
     */
    private void setupListeners() {
        //Time Listener, ez a lsitener fiygeli, hogy 10 milisecundumokként újra rajzolja
        this.delay = 10;
        this.timer = new Timer(delay, timerListener);
        this.timer.start();
        //direction Listener
        this.addKeyListener(dirListener);
    }

    /**
     * fillMaze() metódus tölti fel a blocks illetve coins dinamikus tömböket,
     * blokkokkal és coinokkal két része van, az egyik a blokk, másik a coin
     * blokkok kézzel vannak beégetve, míg a coinok véletlen szerűen vannak,
     * boardCoin darab, négyzetek közepén
     */
    private void fillMaze() {
        //blocks
        for (int i = 0; i < boardRowNumb; ++i) {
            blocks.add(new Pos(0, blockWidth * i));
            blocks.add(new Pos(blockWidth * i, 0));
            blocks.add(new Pos(blockWidth * i, boardRowNumb * blockWidth - blockWidth));
            blocks.add(new Pos(boardRowNumb * blockWidth - blockWidth, blockWidth * i));
        }

        for (int i = 2; i < boardRowNumb - 2; ++i) {
            if (i == 7) {
                continue;
            }
            blocks.add(new Pos(blockWidth * i, blockWidth * 2));
        }

        for (int i = 2; i < boardRowNumb - 2; ++i) {
            if (i == 7) {
                continue;
            }
            blocks.add(new Pos(blockWidth * 2, blockWidth * i));
        }

        for (int i = 4; i < boardRowNumb - 2; ++i) {
            blocks.add(new Pos(blockWidth * i, blockWidth * 4));
            blocks.add(new Pos(blockWidth * i, blockWidth * 8));
            if (i == 7) {
                continue;
            }
            blocks.add(new Pos(blockWidth * i, blockWidth * 6));
        }

        for (int i = 10; i < boardRowNumb - 2; ++i) {
            blocks.add(new Pos(blockWidth * 4, blockWidth * i));
            blocks.add(new Pos(blockWidth * 6, blockWidth * i));
            blocks.add(new Pos(blockWidth * 8, blockWidth * i));
        }

        //3.6
        blocks.add(new Pos(3 * blockWidth, 6 * blockHeight));
        //6.13
        blocks.add(new Pos(6 * blockWidth, 13 * blockHeight));
        //8.9
        blocks.add(new Pos(8 * blockWidth, 9 * blockHeight));

        //11-13, 11-9
        for (int i = 10; i < 13; ++i) {
            for (int j = 12; j > 9; j--) {
                blocks.add(new Pos(i * blockWidth, j * blockHeight));
            }
        }
        
        blocks.add(new Pos(blockWidth *13, blockWidth * 4));

        //blocks.add();
        /**
         * coins véletlen szerűen generálok 1...boardRowNumb/boardColNumb egy
         * számot, majd megszorozva blockWidth/blockHeight-el, kapom meg a
         * négyzetek helyét. hozzáadva blockRad-ot a pont x és y tengehlyez adja
         * meg a négyzet közepét
         */
        for (int i = 0; i < boardCoins;) {
            Pos p = new Pos((new Random().nextInt(boardRowNumb)) * blockWidth, (new Random().nextInt(boardColNumb)) * blockHeight);
            if ((blockCollison(p.x, p.y))
                    && (blockCollison(p.x - blockRad, p.y))
                    && (blockCollison(p.x + blockRad, p.y))
                    && (blockCollison(p.x, p.y - blockRad))
                    && (blockCollison(p.x, p.y + blockRad))) {

                this.coins.add(new Pos(p.x + blockRad, p.y + blockRad));
                ++i;

            }
        }

    }

    /**
     * moveMovables metódus, mely megváltoztatja pacman irányát, ellenörzések
     * után Movable típusú objektumoknak
     */
    private void moveMovables(Movable mov) {

        int diff = 6;
        switch (mov.getDir()) {
            case LEFT:
                if (!(blockCollison((mov.getPos().x - speed - blockRad), mov.getPos().y))
                        || (!(blockCollison((mov.getPos().x - speed - blockRad), mov.getPos().y + blockRad - diff)))
                        || (!(blockCollison((mov.getPos().x - speed - blockRad), mov.getPos().y - blockRad + diff)))) {
                    if (mov instanceof Ghost) {
                        Direction d = ((new Random()).nextInt(2) == 0) ? Direction.UP : Direction.DOWN;
                        mov.changeDir(d);
                    }
                } else {
                    mov.move(-1 * speed, 0);
                }
                break;

            case RIGHT:
                if ((!(blockCollison((mov.getPos().x + 1 * speed + blockRad), mov.getPos().y)))
                        || (!(blockCollison((mov.getPos().x + 1 * speed + blockRad), mov.getPos().y + blockRad - diff)))
                        || (!(blockCollison((mov.getPos().x + 1 * speed + blockRad), mov.getPos().y - blockRad + diff)))) {
                    if (mov instanceof Ghost) {
                        Direction d = ((new Random()).nextInt(2) == 0) ? Direction.UP : Direction.DOWN;
                        mov.changeDir(d);
                    }
                } else {
                    mov.move(1 * speed, 0);
                }
                break;

            case UP:
                if ((!(blockCollison((mov.getPos()).x, mov.getPos().y + -1 * speed - blockRad)))
                        || (!(blockCollison((mov.getPos().x + blockRad - diff), mov.getPos().y + -1 * speed - blockRad)))
                        || (!(blockCollison((mov.getPos().x - blockRad + diff), mov.getPos().y + -1 * speed - blockRad)))) {
                    if (mov instanceof Ghost) {
                        Direction d = ((new Random()).nextInt(2) == 0) ? Direction.LEFT : Direction.RIGHT;
                        mov.changeDir(d);
                    }
                } else {
                    mov.move(0, -1 * speed);
                }
                break;

            case DOWN:
                if ((!(blockCollison(mov.getPos().x, mov.getPos().y + 1 * speed + blockRad)))
                        || (!(blockCollison((mov.getPos().x + blockRad - diff), mov.getPos().y + 1 * speed + blockRad)))
                        || (!(blockCollison((mov.getPos().x - blockRad + diff), mov.getPos().y + 1 * speed + blockRad)))) {
                    if (mov instanceof Ghost) {
                        Direction d = ((new Random()).nextInt(2) == 0) ? Direction.LEFT : Direction.RIGHT;
                        mov.changeDir(d);
                    }
                } else {
                    mov.move(0, 1 * speed);
                }
                break;
        }
    }

    /**
     *
     * movable objektumok rajzolása színtérbe
     */
    private void drawMovables(Graphics2D g) {
        g.drawImage(this.pacman.getImage(), pacman.getPos().x, pacman.getPos().y, this);
        for (Movable m : ghosts) {
            g.drawImage(m.getImage(), m.getPos().x, m.getPos().y, this);
        }
    }

    /**
     *
     * státusz feliratok rajzolása színtérbe
     */
    private void drawStatus(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawString("COLLECTED POINTS: " + this.collectedCoins, 10, 10);
        g2d.drawString("SPEED: " + this.speed, 10, 20);
        g2d.drawString("ELAPSED TIME: " + (System.currentTimeMillis() - this.gameStartTime) / 1000 + " sec", 10, 30);
    }

    /**
     *
     * labirintus kirajzolása színtérbe, coinokkal együtt
     */
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

    /**
     * p és q Pos típusú pontokat leellenörzi, közös körlapon vannak-e
     *
     * @param p
     * @param q
     * @param rad
     * @return p és q Pos típusú pontokat leellenörzi, közös körlapon vannak-e
     */
    private boolean checkIsNotCollided(Pos p, Pos q, int rad) {

        if ((p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y) < (rad) * (rad)) {
            return false;
        }

        return true;
    }

    /**
     * speed növelésse m-mel, 1 és 6 között max, ennyivel több pixelt fog ugrani
     * két kirajzolás között
     *
     * @param m
     */
    private void setSpeed(int m) {
        if (!(this.speed + m > this.maxspeed || this.speed + m < this.minspeed)) {
            this.speed += m;
        }
    }

    /**
     * x és y pont bele esik-e coinok körüli körlpra
     *
     * @param x
     * @param y
     */
    private void coinCollison() {
    int x = this.pacman.getPos().x;
    int y = this.pacman.getPos().y;
        for (Pos p : coins) {
            if (!(checkIsNotCollided(p, new Pos(x + this.blockRad, y + this.blockRad), this.blockRad + 5))) {
                coins.remove(p);
                this.collectedCoins++;
                return;
            }
        }
    }

    /**
     * van-e szelemekkel collison, szellemek köüli körlapon
     */
    private void ghostCollison() {
        for (Movable p : ghosts) {
            if (!(checkIsNotCollided(p.getPos(), new Pos(pacman.getPos().x, pacman.getPos().y), this.blockRad + 5))) {
                this.speed = 0;
                this.gameEnded = true;
                this.pacman.setDead(true);
            }
        }
    }

    /**
     * x és y pont bele esik e blokkok körüli körlapon
     *
     * @param x
     * @param y
     * @return x és y pont bele esik e blokkok körüli körlapon
     */
    private boolean blockCollison(int x, int y) {
        for (Pos p : blocks) {
            if (!checkIsNotCollided(new Pos(x, y), new Pos(p.x, p.y), this.blockRad)) {
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

    /**
     * minden kirajzolásnál meghívott metódus
     *
     * @param g
     */
    private void drawBoard(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        moveMovables(this.pacman);
        for (Movable ghost : ghosts) {
            moveMovables(ghost);
        }
        coinCollison();
        ghostCollison();
        drawMaze(g2d);
        drawMovables(g2d);
        drawStatus(g2d);
        checkCondition();
    }

    /**
     *
     * @return visszaadja, hogy a pacman objektum dead értékét
     */
    public boolean isDead() {
        return this.pacman.isDead();
    }

    public void checkCondition() {
        if ((this.collectedCoins >= boardCoins) || (pacman.isDead())) {
            this.gameEnded = true;
        }
    }
    
    public long gameEndedTime(){
        return (System.currentTimeMillis() - this.gameStartTime) / 1000;
    }

    public boolean isGameEnded() {
        return this.gameEnded;
    }
    
    public int getCollectedCoins(){
        return this.collectedCoins;
    }

}
