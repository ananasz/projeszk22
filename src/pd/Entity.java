package pd;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Daniel
 */
public class Entity {

    private ImageIcon image;
    private double x, y, rot;

    public Entity(ImageIcon image, double x, double y, double rot) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.rot = rot;
    }

    public Image getImage() {
        return image.getImage();
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
}
