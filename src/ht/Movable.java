/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht;

/**
 *
 * @author Herendi Tibor
 */
public interface Movable {
    public void move(int x, int y);
    public void dead(boolean b);
    public int getPosX();
    public int getPosY();
    public Direction getDir();
    public void changeDir(Direction dir);
}

enum Direction{UP,RIGHT,DOWN,LEFT};
