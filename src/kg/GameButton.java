package kg;

import javax.swing.JButton;

class GameButton extends JButton {

    private final int columnIndex;
    private final int rowIndex;

    public GameButton(int i, int j) {
        this.rowIndex = i;
        this.columnIndex = j;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }
    
    
}
