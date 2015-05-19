/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
    private Color c;
    private String endGameText;
    private int boardCoins;

    public EndGamePanel(boolean isDead, long gameEndedTime, int collectedCoins, int boardCoins) {
        super();
        this.chosenMenu = 0;
        this.isDead = isDead;
        this.gameEndedTime = gameEndedTime;
        this.collectedCoins = collectedCoins;
        this.c = null;
        this.endGameText = null;
        this.boardCoins = boardCoins;
    }

    public int getBoardCoins() {
        return boardCoins;
    }

    public boolean isDead() {
        return isDead;
    }

    public int getCollectedCoins() {
        return collectedCoins;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setupScreen(g);

    }

    private void setupScreen(Graphics g) {
        if (isDead) {
            c = Color.red;
            endGameText = "YOU DIED";
            g.setFont(new Font("Serif", Font.BOLD, 60));
            g.setColor(c);
            g.drawString(endGameText, this.getWidth() / 2 - 80, 50);
        } else {
            c = Color.green;
            endGameText = "YOU WON";
            g.setFont(new Font("Serif", Font.BOLD, 60));
            g.setColor(c);
            g.drawString(endGameText, this.getWidth() / 2 - 80, 50);
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

    public Color getColor() {
        return this.c;
    }

    public String getEndGameText() {
        return this.endGameText;
    }
}
