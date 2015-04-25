package pd;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author Daniel
 */
public class GameLogic implements MouseMotionListener, MouseListener {

    private Timer timer;
    private PDFrame frame;
    private Point cursor;
    private LinkedList<Entity> enemies = new LinkedList<>();
    private Random rndGen = new Random();
    private boolean alreadyShot = false;
    private ImageIcon explosionImage;
    private Entity explosion;

    public GameLogic(PDFrame frame) {
        timer = new Timer(50, new TimerListener());
        timer.start();
        this.frame = frame;
        cursor = new Point(0, 0);
        
        explosionImage = new ImageIcon(
            getClass().getResource("textures\\Explosion.gif"));
        explosion = new Entity("Explosion",
                            explosionImage,
                            cursor.x - 90,
                            cursor.y - 90,
                            0.0d);
    }

    public Point getCursor() {
        return cursor;
    }
    
    public boolean isOnTarget(Entity e) {
        if(cursor.x > e.getCenterX() - 30 &&
           cursor.x < e.getCenterX() + 30 &&
           cursor.y > e.getCenterY() - 50 &&
           cursor.y < e.getCenterY() + 100) {
            
            return true;
        }
        return false;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        cursor = me.getLocationOnScreen();
        cursor.y -= frame.getLocation().y;
        cursor.x -= frame.getLocation().x;
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        cursor = me.getLocationOnScreen();
        cursor.y -= frame.getLocation().y;
        cursor.x -= frame.getLocation().x;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
        if(alreadyShot) {
            frame.getPanel().getEntities().remove(explosion);
            explosion.setX(cursor.x - 90);
            explosion.setY(cursor.y - 90);
            frame.getPanel().getEntities().add(explosion);
            explosionImage.getImage().flush();
        } else {
            explosion.setX(cursor.x - 90);
            explosion.setY(cursor.y - 90);
            frame.getPanel().getEntities().add(explosion);
            alreadyShot = true;
        }
        
        for(Entity e: enemies) {
            if(isOnTarget(e) && e.isAlive()) {
                e.setImage(
                        new ImageIcon(getClass().getResource("textures\\T-34_destroyed.png")));
                e.destroyed();
                
                int i = frame.getPanel().getEntities().indexOf(e);
                frame.getPanel().getEntities().get(i).setImage(
                        new ImageIcon(getClass().getResource("textures\\T-34_destroyed.png")));
                frame.getPanel().getEntities().get(i).destroyed();
            }
        }
    }
    
    public void addEnemy() {
        Entity newEnemy = new Entity(
                            "T-34", 
                            new ImageIcon(getClass().getResource("textures\\T-34.png")),
                            rndGen.nextInt(frame.getWidth() - 200),
                            -200,
                            0.0d);
        enemies.add(newEnemy);
        frame.getPanel().getEntities().add(newEnemy);
    }
    
    public void moveEnemies() {
        for(Entity e: enemies) {
            if(e.isAlive()) {
                e.setY(e.getY() + 6);
                int i = frame.getPanel().getEntities().indexOf(e);
                frame.getPanel().getEntities().get(i).setY(e.getY());
            } else {
                e.decreaseRemoveCooldown();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //DO NOTHING
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //DO NOTHING
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //DO NOTHING
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //DO NOTHING
    }

    private class TimerListener extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Entity turret = frame.getPanel().getEntities().get(2);
            double newRot = Math.toDegrees(Math.atan2(cursor.y - turret.getCenterY(), cursor.x - turret.getCenterX())) + 90.0d;
            turret.setRot(newRot);
            
            moveEnemies();
            
            if(rndGen.nextInt(149) == 0) {
                addEnemy();
            }
            
            frame.repaint();
        }
    }
}
