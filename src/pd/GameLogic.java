package pd;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.AbstractAction;
import javax.swing.Timer;

/**
 *
 * @author Daniel
 */
public class GameLogic implements MouseMotionListener {

    private Timer timer;
    private PDFrame frame;
    private Point cursor;

    public GameLogic(PDFrame frame) {
        timer = new Timer(50, new TimerListener());
        timer.start();
        this.frame = frame;
        cursor = new Point(0, 0);
    }

    public Point getCursor() {
        return cursor;
    }

    private class TimerListener extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Entity turret = frame.getPanel().getEntities().get(2);
            double newRot = Math.toDegrees(Math.atan2((cursor.y - 130) - turret.getCenterY(), (cursor.x - 110) - turret.getCenterX())) + 90.0d;
            turret.setRot(newRot);

            frame.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        cursor = me.getLocationOnScreen();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        cursor = me.getLocationOnScreen();
    }
}
