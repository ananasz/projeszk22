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
public class PacMan implements Movable {

    private int x, y;
    private boolean dead;
    private Direction dir;

    private Image currentImage;
    private Image imageUp;
    private Image imageDown;
    private Image imageLeft;
    private Image imageRight;
    
    public final int pacman_text_height = 58;
    public final int pacman_text_width = 58;

    public PacMan(int x, int y) {
        this.x = x;
        this.y = y;
        this.dead = false;
        this.dir = Direction.RIGHT;
        setupImages();

    }

    private void setupImages() {
        imageDown = new ImageIcon(getClass().getResource("textures\\pacman_down.png")).getImage();
        imageUp = new ImageIcon(getClass().getResource("textures\\pacman_up.png")).getImage();
        imageLeft = new ImageIcon(getClass().getResource("textures\\pacman_left.png")).getImage();
        imageRight = new ImageIcon(getClass().getResource("textures\\pacman_right.png")).getImage();
        this.currentImage = imageRight;
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
        switch (dir) {
            case UP:
                this.currentImage = this.imageUp;
                break;
            case DOWN:
                this.currentImage = this.imageDown;
                break;
            case LEFT:
                this.currentImage = this.imageLeft;
                break;
            case RIGHT:
                this.currentImage = this.imageRight;
                break;
        }
    }

    @Override
    public Image getImage() {
        return this.currentImage;
    }

}
