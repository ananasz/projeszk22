/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Herendi Tibor
 */
public class EndGamePanel extends JPanel {

    private int chosenMenu;
    private boolean isDead;
    private long gameEndedTime;
    private int collectedCoins;

    public EndGamePanel(boolean isDead, long gameEndedTime, int collectedCoins) {
        super();
        this.chosenMenu = 0;
        this.isDead = isDead;
        this.gameEndedTime = gameEndedTime;
        this.collectedCoins = collectedCoins;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //g.drawImage(new ImageIcon(getClass().getResource("textures\\pacman_logo_done_yellowgreen.png")).getImage(), 0, 0, null);
        if (isDead) {
            g.setFont(new Font("Serif", Font.BOLD, 60));
            g.setColor(Color.red);
            g.drawString("YOU DIED", this.getWidth() / 2 - 80, 50);
        } else {
            g.setFont(new Font("Serif", Font.BOLD, 60));
            g.setColor(Color.green);
            g.drawString("YOU WON", this.getWidth() / 2 - 80, 50);
        }

        g.setFont(new Font("Serif", Font.BOLD, 12));
        if (chosenMenu == 0) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.white);
        }
        g.drawString("NEW GAME", this.getWidth() / 2, 250);
        if (chosenMenu == 1) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.white);
        }
        g.drawString("EXIT GAME", this.getWidth() / 2, 270);

        g.setColor(Color.white);
        g.drawString("GAME TIME: " + gameEndedTime + " sec", this.getWidth() / 2, 200);
        g.drawString("COLLECTED COINS: " + collectedCoins, this.getWidth() / 2, 210);
    }

    public void incChosenMenu() {
        this.chosenMenu = (chosenMenu + 1) % 2;
    }

    public void descChosenMenu() {
        this.chosenMenu = (Math.abs(chosenMenu - 1)) % 2;
    }

    public int getChosenMenu() {
        return this.chosenMenu;
    }
}
