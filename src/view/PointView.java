package view;

import javax.swing.*;
import java.awt.*;

public class PointView extends JPanel {
    private int x, y, index;
    private boolean hovered;
    private GridBagConstraints constraints;
    private Point modelPoint;

    public PointView(int x, int y, int index){
        this.x = x;
        this.y = y;
        this.index = index;
        this.hovered = false;
        setPreferredSize(new Dimension(25,25));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        if(index>=0){
            g.fillOval(5,5,15,15);
        }
        else{
            g.drawArc(5,5,15,15,0,360);
        }
        if(hovered){
            g.drawRect(1,1,23,23);
        }
    }

    public String toString(){
        return "("+x+";"+y+") > "+index;
    }

    public boolean getHovered(){
        return hovered;
    }

    public void setHovered(boolean b){
        this.hovered = b;
    }

    public void setIndex(int index){
        this.index = index;
    }
}