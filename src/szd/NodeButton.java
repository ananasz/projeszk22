package szd;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JButton;
import projeszk22.Consts;

public class NodeButton extends JButton{
    
    private boolean active;
    private final boolean isOrigin;
    private final int index;
    
    public NodeButton(int i, boolean isOrigin){
        super(Consts.SZD_NODELABELS[i]);
        this.isOrigin = isOrigin;
        index = i;
        setMargin(new Insets(3, 3, 3, 3));
    }
    
    public void setActive(boolean active){
        this.active = active;
    }

    public boolean isOrigin() {
        return isOrigin;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(active){
            g.setColor(Color.green);
            g.drawRect(1, 1, getWidth()-3, getHeight()-3);
        }else
            super.paintComponent(g);
    }   

    int getNodeIndex() {
        return index;
    }
}