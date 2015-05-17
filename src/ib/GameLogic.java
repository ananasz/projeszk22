package ib;

import java.util.Random;
import static projeszk22.Consts.*;

public class GameLogic {

    private int size = IB_DEFAULT_SIZE;

    private final int[][] table;
    private boolean gameOver = false;

    public GameLogic(int size) {
        this.size = size;
        table = new int[size][size];
        newGame();
    }

    public final void newGame() {
        gameOver = false;
        initializeTable();
        randomizeTable();
    }

    private void initializeTable() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = i * size + j;
            }
        }
    }

    private void randomizeTable() {
        Random rnd = new Random();
        int i = 0;
        while (i <= IB_NUMBER_OF_RANDOMIZATION) {
            switch (rnd.nextInt() % 4) {
                case (0):
                    i += slideUp();
                    break;
                case (1):
                    i += slideDown();
                    break;
                case (2):
                    i += slideLeft();
                    break;
                case (3):
                    i += slideRight();
                    break;
            }
        }
    }

    public int slideDown() {
        return slide(-1, 0);
    }

    public int slideUp() {
        return slide(1, 0);
    }

    public int slideRight() {
        return slide(0, -1);
    }

    public int slideLeft() {
        return slide(0, 1);
    }

    /**
     *
     * @param x : 1 for up, -1 for down
     * @param y : 1 for left, -1 for right
     * @return : 1 ha sikeres lépés, 0 ha nem
     */
    public int slide(int x, int y) {
        if (Math.abs(x) + Math.abs(y) != 1 || gameOver) {
            return 0;
        }

        for (int i = (x == 1) ? 1 : 0; i < ((x == -1) ? size - 1 : size); i++) {
            for (int j = (y == 1) ? 1 : 0; j < ((y == -1) ? size - 1 : size); j++) {
                if (table[i][j] == 0) {
                    int s = table[i][j];
                    table[i][j] = table[i - x][j - y];
                    table[i - x][j - y] = s;
                    return 1;
                }
            }
        }
        return 0;
    }

    public int getSize() {
        return size;
    }

    public int getNum(int x, int y) {
        return table[x][y];
    }

    public int[][] getTable() {
        return table;
    }

    public boolean isGameOverLogic() {
        boolean tmp = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (table[i][j] != i * size + j) {
                    tmp = false;
                }
            }
        }
        return tmp;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
