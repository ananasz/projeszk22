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

public class PacMan implements Movable{
    private int x,y;
    private boolean dead;
    private Direction dir;
    
    private Image img_up;
    private Image img_down;
    private Image img_left;
    private Image img_right;
    
    public PacMan(){
        this.x = 1;
        this.y = 1;
        this.dead = false;
        this.dir = Direction.RIGHT;
    }
    
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
    public int getPosX() {
        return this.x;
    }
    
    @Override
    public int getPosY() {
        return this.y;
    }

    @Override
    public Direction getDir() {
        return this.dir;
    }

    @Override
    public void changeDir(Direction dir) {
        this.dir = dir;
    }
    
}