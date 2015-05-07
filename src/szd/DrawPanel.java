package szd;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;

public class DrawPanel extends JPanel{

    private MatrixReader matrix;
    private int mSize;
    private final String[] labels = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"};
    public void setMatrix(MatrixReader m){
        matrix = m;
        mSize = m.getSize();
    }
    
    private Point getCoordinates(int i){
            int x0 = getWidth()/2;
            int y0 = getHeight()/2;
            int r = mSize*10+40;
            int x = (int) Math.round( x0 + r * Math.cos( i*360/mSize * Math.PI / 180) );
            int y = (int) Math.round( y0 + r * Math.sin( i*360/mSize * Math.PI / 180) );
            return new Point(x, y);
    }
    
    private Point getLabelPoint(Point p1, Point p2){
        int x = p1.x + Math.round( (p2.x - p1.x) * 0.4f);
        int y = p1.y + Math.round( (p2.y - p1.y) * 0.4f);
        return new Point(x, y);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for(int j = 0; j < mSize; j++){
            Point jCoord = getCoordinates(j);
            for(int i = 0; i < j; i++){ //élek kirajzolása
                int weight = matrix.get(i, j);
                if( weight != 0){
                    if(weight < 0){
                        g.setColor(Color.green);
                        weight = -weight;
                    }else{
                        g.setColor(Color.black);
                    }
                    Point iCoord = getCoordinates(i);
                    g.drawLine(iCoord.x, iCoord.y, jCoord.x, jCoord.y);
                    
                    //élsúlyok kirajzolása háttérrel
                    Point weightLabelCoord = getLabelPoint(iCoord, jCoord);
                    Color tmpColor = g.getColor();
                    g.setColor(Color.white);
                    g.fillRect(weightLabelCoord.x, weightLabelCoord.y-10, 15, 10);
                    g.setColor(tmpColor);
                    g.drawString(weight+"", weightLabelCoord.x, weightLabelCoord.y);
                }
            }
        }
        for(int i = 0; i < mSize; i++){//csúcsok kirajzolása, külön ciklusban hogy mindegyik él fölé legyen rajzolva
            Point c = getCoordinates(i);
            g.setColor(Color.white);
            g.fillOval(c.x-10, c.y-10, 20,20);
            g.setColor(Color.black);
            g.drawString(labels[i], c.x-5, c.y+5);
            g.drawOval(c.x-10, c.y-10, 20,20);
        }
        
    }
   
    
}
