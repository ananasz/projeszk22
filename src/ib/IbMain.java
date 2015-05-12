package ib;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class IbMain extends JFrame {

    public final static int IB_MENU_HEIGHT = 50;
    public final static int IB_FRAME_HEIGHT = 500 + IB_MENU_HEIGHT;
    public final static int IB_FRAME_WIDTH = 500;
    public final static String IB_FRAME_TITLE = "Slide Puzzle";
    public final static String IB_NEW_GAME = "Új játék";
    public final static String IB_EXIT = "Kilépés";
    public final static String IB_MENU = "Menü";

    //private JPanel menu;
    public IbMain() {
        initializeFrame();
        setupFrame();
        setupMenu();
    }

    private void setupMenu() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem newGameItem, exitItem;

        menuBar = new JMenuBar();

        newGameItem = new JMenuItem();
        newGameItem.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
        newGameItem.setText(IB_NEW_GAME);

        exitItem = new JMenuItem();
        exitItem.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        exitItem.setText(IB_EXIT);

        menu = new JMenu(IB_MENU);
        menu.add(newGameItem);
        menu.add(exitItem);
        
        menuBar.add(menu);

        setJMenuBar(menuBar);
    }

    private void newGame() {

    }

    private void exit() {

    }

    private void setupFrame() {

    }

    private void initializeFrame() {
        setTitle(IB_FRAME_TITLE);
        setSize(IB_FRAME_WIDTH, IB_FRAME_HEIGHT);
    }

}
