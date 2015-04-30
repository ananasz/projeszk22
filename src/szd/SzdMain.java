package szd;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static projeszk22.Consts.*;

public class SzdMain extends JFrame{
    
    final JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
    private MatrixReader matrix;
    
    private final Action openFile = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            int ret = fc.showOpenDialog(null);
            if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    matrix = new MatrixReader(fc.getSelectedFile());
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Hiba! A megadott fájl nem található");
                } catch (MatrixException ex) {
                    JOptionPane.showMessageDialog(null, "Hiba a mátrix beolvasásakor: " + ex.getMessage());
                }
            }
        }
    };
    
    public SzdMain(){
        setupFrame();
        setupMenu();
    }
    
    private void setupFrame(){
        setTitle(SZD_TITLE);
        setSize(SZD_DEF_WIDTH, SZD_DEF_HEIGHT);
    } 
    
    private void setupMenu(){
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem openItem;

        menuBar = new JMenuBar();

        openItem = new JMenuItem(SZD_MENU_OPEN);
        openItem.setAction(openFile);
        
        menu = new JMenu(SZD_MENU_FILE);
        menu.add(openItem);
        menuBar.add(menu);

        setJMenuBar(menuBar);
    }
}
