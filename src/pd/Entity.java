package pd;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Daniel
 */
public class Entity {

    private String name;
    private ImageIcon image;
    private double x, y, rot;
    private boolean alive = true;
    private int removeCooldown = 100;

    public Entity(String name, ImageIcon image, double x, double y, double rot) {
        this.name = name;
        this.image = image;
        this.x = x;
        this.y = y;
        this.rot = rot;
    }
    
    public Entity(String name, ImageIcon image) {
        this(name, image, 0.0d, 0.0d, 0.0d);
    }
    
    public void decreaseRemoveCooldown() {
        if(removeCooldown > -1) {
            removeCooldown--;
        }
        if(removeCooldown == 0) {
            image = new ImageIcon(getClass().getResource("textures\\Blank.png"));
        }
    }
    
    public double getCenterX() {
        return x + (image.getIconWidth() / 2);
    }
    
    public double getCenterY() {
        return y + (image.getIconHeight() / 2);
    }

    public String getName() {
        return name;
    }

    public ImageIcon getImageIcon() {
        return image;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRot() {
        return rot;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRot(double rot) {
        this.rot = rot;
    }

    public void destroyed() {
        alive = false;
    }
}
