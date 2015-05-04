package pd;

import java.awt.HeadlessException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Daniel
 */
public class PDFrame extends JFrame {

    private CustomPanel panel;
    private GameLogic logic;

    public PDFrame() {
        setTitle("TankHunt - PD");
        setSize(1024, 768);
        setLocation(100, 100);
        setResizable(false);

        panel = new CustomPanel();
        panel.setLayout(null);
        add(panel);
        addEntities();
        logic = new GameLogic(this);
        addMouseMotionListener(logic);
        addMouseListener(logic);
    }

    private void addEntities() {
        Entity terrain = new Entity("Terrain", new ImageIcon(getClass().getResource("textures\\Terrain.jpg")));
        Entity tigerHull = new Entity("TigerHull", new ImageIcon(getClass().getResource("textures\\Tiger_hull.png")), 525, 450, 0);
        Entity tigerTurret = new Entity("TigerTurret", new ImageIcon(getClass().getResource("textures\\Tiger_turret.png")), tigerHull.getX(), tigerHull.getY(), 0);

        panel.getEntities().add(terrain);
        panel.getEntities().add(tigerHull);
        panel.getEntities().add(tigerTurret);
    }

    public CustomPanel getPanel() {
        return panel;
    }

    public GameLogic getLogic() {
        return logic;
    }
}
