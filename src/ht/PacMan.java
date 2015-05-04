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
    int x,y;
    boolean dead;
    Image img;
    Direction dir;
    
    public PacMan(){
        this.x = 6;
        this.y = 1;
        this.dead = false;
        this.dir = Direction.LEFT;
        //this.img = new Image("classic-pacman-11.gif");
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
    public int[] getPos() {
        int pos[] = {this.x,this.y};
        return pos;
    }

    @Override
    public Direction getDir() {
        return this.dir;
    }
    
}