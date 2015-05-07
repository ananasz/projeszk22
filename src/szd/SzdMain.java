package szd;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static projeszk22.Consts.*;

public class SzdMain extends JFrame implements ActionListener{
    
    final JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
    private MatrixManager matrix;
    private DrawPanel drawPanel;
    private JPanel fromPanel, toPanel;
    private final ArrayList<NodeButton> fromButtons, toButtons;
    private int fromNode = -1, toNode = -1;
    private MatrixPathfinder pathFinder;
    
    private final Action openFile = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            int ret = fc.showOpenDialog(null);
            if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    matrix = new MatrixManager(fc.getSelectedFile());
                    drawPanel.setMatrix(matrix);
                    pathFinder = new MatrixPathfinder(matrix);
                    drawPanel.repaint();
                    setupButtons();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, SZD_ERR_FILE_NOT_FOUND);
                } catch (MatrixException ex) {
                    JOptionPane.showMessageDialog(null, SZD_ERR_MATRIX_GENERAL + ex.getMessage());
                }
            }
        }
    };
    
    public SzdMain(){
        fromButtons = new ArrayList<>();
        toButtons = new ArrayList<>();
        setupFrame();
        setupMenu();
        setupLayout();
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
        
        menu = new JMenu(SZD_MENU_FILE);
        menu.add(openItem);
        menuBar.add(menu);

        setJMenuBar(menuBar);
    }
    
    private void setupLayout(){
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        
        fromPanel = new JPanel();
        toPanel = new JPanel();
        controlPanel.add(fromPanel);
        controlPanel.add(toPanel);
        
        drawPanel = new DrawPanel();
        drawPanel.setMinimumSize(new Dimension(SZD_DEF_WIDTH, SZD_DEF_HEIGHT-150));
        controlPanel.setMinimumSize(new Dimension(SZD_DEF_WIDTH, 150));
        controlPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        
        container.add(drawPanel);
        container.add(controlPanel);
        getContentPane().add(container);
    }

    private void setupButtons() {
        fromPanel.removeAll();
        toPanel.removeAll();
        fromPanel.add(new JLabel(SZD_LABEL_FROM));
        toPanel.add(new JLabel(SZD_LABEL_TO));
        
        int count = matrix.getSize();
        
        for(int i = 0; i < count; i++){
            NodeButton btn = new NodeButton(i, true);
            fromButtons.add(btn);
            fromPanel.add(btn);
            btn.addActionListener(this);
        }
        for(int i = 0; i < count; i++){
            NodeButton btn = new NodeButton(i, false);
            toButtons.add(btn);
            toPanel.add(btn);
            btn.addActionListener(this);
        }
        getContentPane().revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NodeButton pressed = (NodeButton) e.getSource();
        if(pressed.isOrigin()){
            for( NodeButton b : fromButtons){
                if(b == pressed){
                    fromNode = b.getNodeIndex();
                    b.setActive(true);
                }else
                    b.setActive(false);
                b.repaint();
            }
        }else{
            for( NodeButton b : toButtons){
                if(b == pressed){
                    toNode = b.getNodeIndex();
                    b.setActive(true);
                }else
                    b.setActive(false);
                b.repaint();
            }
        }
            
        if( toNode != -1 && fromNode != -1){
            try{
                drawPanel.setPath(pathFinder.getShortestPath(fromNode, toNode));
                drawPanel.repaint();
            }catch(PathException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
}