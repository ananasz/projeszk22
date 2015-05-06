package kg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import kg.ColorGameLogic;

class SwingColorGameFrame extends JFrame {

    private ColorGameLogic cgl;
    private Action newGameAction = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            cgl.newGame();
            setButtonColors();
        }
    };
    private JPanel gamePanel;
    private ActionListener cellActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameButton gb = (GameButton) e.getSource();
            int x = gb.getRowIndex();
            int y = gb.getColumnIndex();
            cgl.setCellColor(x, y);
            setButtonColors();
            if (cgl.isGameWon()) {
                
                cgl.newGame();
            }
        }
    };

    public SwingColorGameFrame(ColorGameLogic cgl) {
        this.cgl = cgl;
        setTitle("Színkirakósdi");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(40, 400);
        setLayout(new BorderLayout());
        JButton newGameButton = new JButton(newGameAction);
        newGameButton.setText("Új játék");

        add(newGameButton, BorderLayout.NORTH);
        int width = cgl.getWidth();
        int height = cgl.getHeight();
        gamePanel = new JPanel(new GridLayout(width, height));
        add(gamePanel, BorderLayout.CENTER);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                JButton jButton = new GameButton(i, j);
                jButton.setPreferredSize(new Dimension(100, 100));
                jButton.addActionListener(cellActionListener);
                gamePanel.add(jButton);

            }
        }
        setButtonColors();
        pack();
    }

    private void setButtonColors() {
        for (Component component : gamePanel.getComponents()) {
            GameButton gb = (GameButton) component;
            int x = gb.getRowIndex();
            int y = gb.getColumnIndex();
            int cellColor = cgl.getCellColor(x, y);
            Color color = null;
            switch (cellColor) {
                case 0:
                    color = Color.RED;
                    break;
                case 1:
                    color = Color.WHITE;
                    break;
                case 2:
                    color = Color.GREEN;
                    break;
            }

            component.setBackground(color);
        }
    }
}
