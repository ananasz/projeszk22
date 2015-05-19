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
enum GhostColor {

    RED, ORANGE, CYAN, PINK
};

public class Ghost implements Movable {

    private int x, y;
    private boolean dead;
    private Direction dir;
    private Image leftImg;
    private Image rightImg;
    private Image upImg;
    private Image downImg;
    private Image currImg;

    public final int ghost_text_height = 58;
    public final int ghost_text_width = 58;

    public Ghost(int x, int y, GhostColor c) {
        this.x = x;
        this.y = y;
        this.dead = false;
        this.dir = Direction.RIGHT;
        setupImages(c);

    }

    private void setupImages(GhostColor c) {
        switch (c) {
            case RED:
                this.leftImg = new ImageIcon(getClass().getResource("textures\\ghost_red_left.png")).getImage();
                this.rightImg = new ImageIcon(getClass().getResource("textures\\ghost_red_right.png")).getImage();
                this.downImg = new ImageIcon(getClass().getResource("textures\\ghost_red_down.png")).getImage();
                this.upImg = new ImageIcon(getClass().getResource("textures\\ghost_red_up.png")).getImage();
                break;
            case PINK:
                this.leftImg = new ImageIcon(getClass().getResource("textures\\ghost_pink_left.png")).getImage();
                this.rightImg = new ImageIcon(getClass().getResource("textures\\ghost_pink_right.png")).getImage();
                this.downImg = new ImageIcon(getClass().getResource("textures\\ghost_pink_down.png")).getImage();
                this.upImg = new ImageIcon(getClass().getResource("textures\\ghost_pink_up.png")).getImage();
                break;
            case CYAN:
                this.leftImg = new ImageIcon(getClass().getResource("textures\\ghost_cyan_left.png")).getImage();
                this.rightImg = new ImageIcon(getClass().getResource("textures\\ghost_cyan_right.png")).getImage();
                this.downImg = new ImageIcon(getClass().getResource("textures\\ghost_cyan_down.png")).getImage();
                this.upImg = new ImageIcon(getClass().getResource("textures\\ghost_cyan_up.png")).getImage();
                break;
            case ORANGE:
                this.leftImg = new ImageIcon(getClass().getResource("textures\\ghost_orange_left.png")).getImage();
                this.rightImg = new ImageIcon(getClass().getResource("textures\\ghost_orange_right.png")).getImage();
                this.downImg = new ImageIcon(getClass().getResource("textures\\ghost_orange_down.png")).getImage();
                this.upImg = new ImageIcon(getClass().getResource("textures\\ghost_orange_up.png")).getImage();
                break;
        }

        this.currImg = rightImg;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void setDead(boolean b) {
        this.dead = b;
    }

    @Override
    public Pos getPos() {
        return new Pos(x, y);
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
            case UP:
                this.currImg = this.upImg;
                break;
            case DOWN:
                this.currImg = this.downImg;
                break;
        }
    }

    @Override
    public Image getImage() {
        return this.currImg;
    }

    @Override
    public boolean isDead() {
        return this.dead;
    }

}
