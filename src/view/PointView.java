package view;

import model.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PointView extends JPanel {
    private int x, y;                               // X-coordinate and Y-coordinate of the model view in the grid
    private int index;                              // view status (latest line it belongs to)
    private boolean hovered;                        // Flag: true if hovered (by the mouse), false if not
    private GridBagConstraints constraints;         // Displaying constraints
    private Point modelPoint;                       // direct reference to the model point
    private ArrayList<LineComponent> lineToDraw;    // Line elements to display around the PointView (JPanel)

    public PointView(Point p){
        this.modelPoint = p;
        this.x = p.getX();
        this.y = p.getY();
        this.index = p.getIndex();
        this.hovered = false;
        this.lineToDraw = new ArrayList<>();

        setPreferredSize(new Dimension(25,25));
        this.constraints = new GridBagConstraints();
        this.constraints.gridx = x+1;
        this.constraints.gridy = y+1;
        this.constraints.weightx = 1;
        this.constraints.weighty = 1;
        this.constraints.anchor = GridBagConstraints.NORTHWEST;
    }

    public void paintComponent(Graphics g){
        /**
         * Displays the point view according to the object attributes
         */
        super.paintComponent(g);
        if(index>=0){
            g.setColor(Color.BLACK);

            g.fillOval(5,5,15,15);
            g.drawOval(5,5,15,15);

            g.setColor(Color.ORANGE);
            g.setFont(new Font("Serif",Font.PLAIN,10));
            if(Integer.toString(index).length()==1){
                g.drawString(Integer.toString(index),11,16);
            } else if (Integer.toString(index).length()==2){
                g.drawString(Integer.toString(index),8,16);
            } else if (Integer.toString(index).length()>2) {
                g.drawString(Integer.toString(index),5,16);
            }

        } else {
            g.setColor(Color.BLACK);
            g.drawOval(5,5,15,15);
        }
        if(hovered) {
            g.setColor(Color.BLACK);
            g.drawRect(1,1,23,23);
        }
        drawLines(getGraphics());
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

    public GridBagConstraints getConstraints(){
        return constraints;
    }

    public void updateView(){
        /**
         * Updates the view: removes current graphics and repaint new ones
         */
        this.removeAll();
        this.paint(this.getGraphics());
    }

    /**
     * Add non existing line components to draw
     *
     * @param comp  Piece of a line to add to this PointView (JPanel)
     */
    public void addLineComponent(LineComponent comp){
        if(!lineToDraw.contains(comp)){
            lineToDraw.add(comp);
        }
    }

    /**
     * Add line components given a line type
     *
     * @param type  LineType (horizontal, vertical, etc ...)
     */
    public void updateLineToDraw(LineType type){
        if(type==LineType.HORIZONTAL){
            addLineComponent(LineComponent.WEST);
            addLineComponent(LineComponent.EAST);
        } else if(type==LineType.VERTICAL) {
            addLineComponent(LineComponent.NORTH);
            addLineComponent(LineComponent.SOUTH);
        } else if(type==LineType.DIAGONAL_DOWN) {
            addLineComponent(LineComponent.NORTHWEST);
            addLineComponent(LineComponent.SOUTHEAST);
        } else if(type==LineType.DIAGONAL_UP) {
            addLineComponent(LineComponent.NORTHEAST);
            addLineComponent(LineComponent.SOUTHWEST);
        }
    }

    /**
     * Graphic method: draw the line components
     *
     * @param g Graphics
     */
    public void drawLines(Graphics g){
        if(lineToDraw.contains(LineComponent.NORTHWEST)){
            g.drawLine(0,0,7,7);
            g.drawLine(0,1,7,8);
        }
        if(lineToDraw.contains(LineComponent.NORTH)){
            g.drawLine(12,0,12,5);
            g.drawLine(13,0,13,5);
        }
        if(lineToDraw.contains(LineComponent.NORTHEAST)){
            g.drawLine(24,0,17,7);
            g.drawLine(24,1,17,8);
        }
        if(lineToDraw.contains(LineComponent.EAST)){
            g.drawLine(20,12,24,12);
            g.drawLine(20,13,24,13);
        }
        if(lineToDraw.contains(LineComponent.SOUTHEAST)){
            g.drawLine(17,17,24,24);
            g.drawLine(17,18,24,25);
        }
        if(lineToDraw.contains(LineComponent.SOUTH)){
            g.drawLine(12,20,12,24);
            g.drawLine(13,20,13,24);
        }
        if(lineToDraw.contains(LineComponent.SOUTHWEST)){
            g.drawLine(0,24,7,17);
            g.drawLine(0,25,7,18);
        }
        if(lineToDraw.contains(LineComponent.WEST)){
            g.drawLine(0,12,5,12);
            g.drawLine(0,13,5,13);
        }
    }

    /**
     * Updates the view with a new Point (usually due to restarting a game)
     * @param p Point
     */
    public void resetView(Point p){
        this.modelPoint = p;
        this.x = p.getX();
        this.y = p.getY();
        this.index = p.getIndex();
        this.hovered = false;
        this.lineToDraw = new ArrayList<>();
        this.updateView();
    }
}