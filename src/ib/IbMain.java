package ib;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import static projeszk22.Consts.*;

public class IbMain extends JFrame implements KeyListener {

    private final GameLogic logic = new GameLogic(IB_DEFAULT_SIZE);
    private final JButton[][] buttons = new JButton[IB_DEFAULT_SIZE][IB_DEFAULT_SIZE];

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
        logic.newGame();
        refresh();
    }

    private void exit() {
        this.setVisible(false);
        logic.newGame();
        refresh();
    }

    private void setupFrame() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(logic.getSize(), logic.getSize()));
        for (int i = 0; i < logic.getSize(); i++) {
            for (int j = 0; j < logic.getSize(); j++) {
                JButton btn = new JButton();
                buttons[i][j] = btn;
                btn.addKeyListener(this);
                panel.add(btn);
            }
        }
        add(panel);
        refresh();

    }

    private void initializeFrame() {
        setTitle(IB_FRAME_TITLE);
        setSize(IB_FRAME_WIDTH, IB_FRAME_HEIGHT);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            logic.slideLeft();
        }

        if (key == KeyEvent.VK_RIGHT) {
            logic.slideRight();
        }

        if (key == KeyEvent.VK_UP) {
            logic.slideUp();
        }

        if (key == KeyEvent.VK_DOWN) {
            logic.slideDown();
        }

        refresh();

        if (logic.isGameOverLogic()) {
            doSomeGameOverThing();
        }
    }

    private void doSomeGameOverThing() {
        if (!logic.isGameOver()) {
            showMessageDialog(null, IB_WIN_TEXT, IB_WIN_TITLE, 1);
        }
        logic.setGameOver(true);
    }

    private void refresh() {
        for (int i = 0; i < logic.getSize(); i++) {
            for (int j = 0; j < logic.getSize(); j++) {
                if (logic.getTable()[i][j] == 0) {
                    buttons[i][j].setText("");
                    buttons[i][j].setBackground(Color.GRAY);
                } else {
                    buttons[i][j].setText(logic.getTable()[i][j] + "");
                    buttons[i][j].setBackground(Color.white);
                }
                if (logic.getTable()[i][j] == i * logic.getSize() + j && logic.getTable()[i][j] != 0) {
                    buttons[i][j].setBackground(IB_GOOD_PLACE_COLOR);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //
    }
}
