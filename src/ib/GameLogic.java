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
        for (int i = 0; i < IB_NUMBER_OF_RANDOMIZATION; i++) {
            switch (rnd.nextInt() % 4) {
                case (0):
                    slideUp();
                    break;
                case (1):
                    slideDown();
                    break;
                case (2):
                    slideLeft();
                    break;
                case (3):
                    slideRight();
                    break;
            }
        }
    }

    public void slideDown() {
        if (!gameOver) {
            for (int i = 0; i < size - 1; i++) {
                for (int j = 0; j < size; j++) {
                    if (table[i][j] == 0) {
                        int s = table[i][j];
                        table[i][j] = table[i + 1][j];
                        table[i + 1][j] = s;
                        return;
                    }
                }
            }
        }
    }

    public void slideUp() {
        if (!gameOver) {
            for (int i = 1; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (table[i][j] == 0) {
                        int s = table[i][j];
                        table[i][j] = table[i - 1][j];
                        table[i - 1][j] = s;
                        return;
                    }
                }
            }
        }
    }

    public void slideRight() {
        if (!gameOver) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size - 1; j++) {
                    if (table[i][j] == 0) {
                        int s = table[i][j];
                        table[i][j] = table[i][j + 1];
                        table[i][j + 1] = s;
                        return;
                    }
                }
            }
        }
    }

    public void slideLeft() {
        if (!gameOver) {
            for (int i = 0; i < size; i++) {
                for (int j = 1; j < size; j++) {
                    if (table[i][j] == 0) {
                        int s = table[i][j];
                        table[i][j] = table[i][j - 1];
                        table[i][j - 1] = s;
                        return;
                    }
                }
            }
        }
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
