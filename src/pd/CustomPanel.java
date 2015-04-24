package pd;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Daniel
 */
public class CustomPanel extends JPanel {

    private LinkedList<Entity> entities = new LinkedList<>();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2D = (Graphics2D) g.create();
        AffineTransform at = new AffineTransform();

        for (Entity e : entities) {
            if (e.getRot() != 0) {
                at.setToRotation(Math.toRadians(e.getRot()), e.getCenterX(), e.getCenterY());
                g2D.setTransform(at);
                g2D.drawImage(e.getImage(), (int) e.getX(), (int) e.getY(), null);
                at.setToRotation(0.0d);
                g2D.setTransform(at);
            } else {
                g2D.drawImage(e.getImage(), (int) e.getX(), (int) e.getY(), null);
            }
        }
    }

    public void setEntities(LinkedList<Entity> entities) {
        this.entities = entities;
    }

    public LinkedList<Entity> getEntities() {
        return entities;
    }
}
