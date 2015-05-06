/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht;

import java.awt.Image;

/**
 *
 * @author Herendi Tibor
 */
public interface Movable {
    public void move(int x, int y);
    public void dead(boolean b);
    public Pos getPos();
    public Direction getDir();
    public void changeDir(Direction dir);
    public Image getImage();
}

enum Direction{UP,RIGHT,DOWN,LEFT};
