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

    public PDFrame() {
        setTitle("TankHunt - PD");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocation(100, 100);
        panel = new CustomPanel();
        panel.setLayout(null);
        add(panel);
        panel.getEntities().add(new Entity(new ImageIcon(getClass().getResource("Terrain.jpg")),0,0,0));
    }
}
