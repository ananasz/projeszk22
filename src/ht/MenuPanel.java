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
public class MenuPanel extends JPanel {

    private int chosenMenu;
    public MenuPanel(){
        super();
        this.chosenMenu = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.black);
        g.drawImage(new ImageIcon(getClass().getResource("textures\\pacman_logo_done_yellowgreen.png")).getImage(), 0, 0, null);

        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.setColor(Color.white);
        g.drawString("MENU:", this.getWidth() / 2, 200);

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
