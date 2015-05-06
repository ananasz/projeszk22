package kg;

import java.util.Random;

/**
 * Egyszerű színkirakós játék megvalósítása
 */
public class ColorGameLogic {
    
    private final int[][] colors;

    public ColorGameLogic(int width, int height) {
        if (width > 0 && height > 0) {
            colors = new int[height][width];
            newGame();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public ColorGameLogic() {
        this(3,3);
    }    

    /**
     * Új játék létrehozásáért felelős funkció
     * A színeket tartalmazó tömböt tölti fel véletlenszerűen a 0,1,2 értékekkel
     */
    public final void newGame() {
        Random r = new Random();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                colors[i][j] = r.nextInt(3);
            }
        }
    }
    
    /**
     * Az adott cellára kattintva a vízszintes és függőleges szomszédok színének megváltoztatása
     * @param i sorindex
     * @param j oszlopindex
     */
    public void setCellColor(int i, int j){
        setSingleCellColor(i,j);
        setSingleCellColor(i + 1,j);
        setSingleCellColor(i - 1,j);
        setSingleCellColor(i,j + 1);
        setSingleCellColor(i,j - 1);
    }    
    
    private void setSingleCellColor(int i, int j) {
        try {
            colors[i][j]++;
            colors[i][j] %= 3;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }
    
    /**
<<<<<<< HEAD
     * A játék nyerési feltételét vizsgáló függvény
     * Ha minden cella egyszínű, a játékos nyert
     * @return 
     */
    public boolean isGameWon() {
        int first = colors[0][0];
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (first != colors[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
=======
>>>>>>> origin/master
     * Egy adott cella színét lekérdező funkció
     * @param i sorindex
     * @param j oszlopindex
     * @return A megadott cella színe
     */
    public int getCellColor(int i, int j){
        return colors[i][j];
    }

    /**
     * A színeket tartalmazó tömb oszlopainak számát adja vissza
     * @return Oszlopok száma
     */
    public int getHeight() {
        return colors.length;
    }

    /**
     * A színeket tartalmazó tömb sorainak számát adja vissza
     * @return Sorok száma
     */
    public int getWidth() {
        return colors[0].length;
    }
}
