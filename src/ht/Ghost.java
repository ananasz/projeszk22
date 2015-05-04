package ht;

import java.awt.Image;

/**
 *
 * @author Herendi Tibor
 */
public class Ghost implements Movable {

    int x, y;
    boolean dead;
    Image img;
    Direction dir;

    @Override
    public void move(int x, int y) {
        this.x+=x;
        this.y+=y;
    }

    @Override
    public void dead(boolean b) {
        this.dead = b;
    }

    @Override
    public int[] getPos() {
        int pos[] = {this.x,this.y};
        return pos;
    }

    @Override
    public Direction getDir() {
        return this.dir;
    }
}
