package ht;

import java.awt.Image;

/**
 *
 * @author Herendi Tibor
 */
public interface Movable {
    public void move(int x, int y);
    public void setDead(boolean b);
    public boolean isDead();
    public Pos getPos();
    public Direction getDir();
    public void changeDir(Direction dir);
    public Image getImage();
}

enum Direction{UP,RIGHT,DOWN,LEFT};
