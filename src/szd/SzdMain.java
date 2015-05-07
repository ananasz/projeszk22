package szd;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
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
    private DrawPanel drawPanel;
    
    private final Action openFile = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            int ret = fc.showOpenDialog(null);
            if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    matrix = new MatrixReader(fc.getSelectedFile());
                    matrix.print();
                    drawPanel.setMatrix(matrix);
                    drawPanel.repaint();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Hiba! A megadott fájl nem található");
                } catch (MatrixException ex) {
                    JOptionPane.showMessageDialog(null, "Hiba a mátrix beolvasásakor: " + ex.getMessage());
                }
            }
        }
    };
    
    private final Action findShortest = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            MatrixPathfinder p = new MatrixPathfinder(matrix);
            drawPanel.setMatrix(p.getShortestPath(0, 1));
            drawPanel.repaint();
        }
    };
    
    public SzdMain(){
        setupFrame();
        setupMenu();
        setupPanel();
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

        openItem = new JMenuItem();
        openItem.setAction(openFile);
        openItem.setText(SZD_MENU_OPEN);
        
        JMenuItem shortI;
        shortI = new JMenuItem();
        shortI.setAction(findShortest);
        shortI.setText("short");
        
        menu = new JMenu(SZD_MENU_FILE);
        menu.add(openItem);
        menu.add(shortI);
        menuBar.add(menu);

        setJMenuBar(menuBar);
    }
    
    private void setupPanel(){
        drawPanel = new DrawPanel();
        getContentPane().add(drawPanel);
    }
}
