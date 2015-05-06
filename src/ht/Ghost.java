/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Herendi Tibor
 */
public class Ghost implements Movable {

    private int x, y;
    private boolean dead;
    private Direction dir;
    private Image leftImg;
    private Image rightImg;
    private Image currImg;
    private Image weakImg;
    
    public final int ghost_text_height = 58;
    public final int ghost_text_width = 58;

    public Ghost(int x, int y) {
        this.x = x;
        this.y = y;
        this.dead = false;
        this.dir = Direction.RIGHT;
        //setupImages();

    }

    private void setupImages() {
        this.leftImg = new ImageIcon(getClass().getResource("textures\\pacman_right.png")).getImage();
        this.rightImg = new ImageIcon(getClass().getResource("textures\\pacman_right.png")).getImage();
        this.weakImg = new ImageIcon(getClass().getResource("textures\\pacman_right.png")).getImage();
        this.currImg = rightImg;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void dead(boolean b) {
        this.dead = b;
    }

    @Override
    public Pos getPos() {
        return new Pos(x,y);
    }

    @Override
    public Direction getDir() {
        return this.dir;
    }

    @Override
    public void changeDir(Direction dir) {
        this.dir = dir;
        switch (dir) {
            case LEFT:
                this.currImg = this.leftImg;
                break;
            case RIGHT:
                this.currImg = this.rightImg;
                break;
        }
    }

    @Override
    public Image getImage() {
        return this.currImg;
    }

}
