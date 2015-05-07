package szd;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;
import static projeszk22.Consts.*;

public class DrawPanel extends JPanel{

    private MatrixManager matrix, path;
    private int mSize;
    
    public void setMatrix(MatrixManager m){
        matrix = m;
        mSize = matrix.getSize();
        path = new MatrixManager(mSize);
    }
    
    public void setPath(MatrixManager path){
        this.path = path;
    }
    
    private Point getCoordinates(int i){
            int x0 = getWidth()/2;
            int y0 = getHeight()/2;
            int r = mSize*20+40;
            int x = (int) Math.round( x0 + r * Math.cos( i*360/mSize * Math.PI / 180) );
            int y = (int) Math.round( y0 + r * Math.sin( i*360/mSize * Math.PI / 180) );
            return new Point(x, y);
    }
    
    private Point getLabelCoordinates(Point p1, Point p2){
        int x = p1.x + Math.round( (p2.x - p1.x) * 0.4f);
        int y = p1.y + Math.round( (p2.y - p1.y) * 0.4f);
        return new Point(x, y);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for(int i = 1; i < mSize; i++){
            Point iCoord = getCoordinates(i);
            for(int j = 0; j < i; j++){ //élek kirajzolása
                float weight = matrix.get(i, j);
                if( weight != 0){
                    
                    g.setColor( path.get(i, j) > 0 ? Color.green : Color.black);
                    
                    Point jCoord = getCoordinates(j);
                    g.drawLine(iCoord.x, iCoord.y, jCoord.x, jCoord.y);
                    
                    //élsúlyok kirajzolása háttérrel
                    String label = ""+weight;
                    Point weightLabelCoord = getLabelCoordinates(iCoord, jCoord);
                    Color lastColor = g.getColor();
                    g.setColor(Color.white);
                    g.fillRect(weightLabelCoord.x-label.length()*3, weightLabelCoord.y-10, label.length()*7, 12);
                    g.setColor(lastColor);
                    g.drawString(label, weightLabelCoord.x-label.length()*3, weightLabelCoord.y);
                }
            }
        }
        for(int i = 0; i < mSize; i++){//csúcsok kirajzolása, külön ciklusban hogy ne takarja él
            Point c = getCoordinates(i);
            g.setColor(Color.white);
            g.fillOval(c.x-10, c.y-10, 20,20);
            g.setColor(Color.black);
            g.drawString(SZD_NODELABELS[i], c.x-5, c.y+5);
            g.drawOval(c.x-10, c.y-10, 20,20);
        }
        
    }
   
    
}
