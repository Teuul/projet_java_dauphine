package controller;

import view.GamePanel;
import view.PointView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseMotionListener, MouseListener {
    final int xOffset = 50+9;           // JFrame X-offset + banner offset
    final int yOffset = 50+33;          // JFrame Y-offset + banner offset
    private GamePanel gameContainer;    // reference to the game container (JPanel)
    private int pointSize;

    public MouseController(GamePanel gamePane){
        this.gameContainer = gamePane;
        this.pointSize = 25;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /**
         * Interaction with the grid (by clicking)
         */
        int coordX = getGridX(e.getX());
        int coordY = getGridY(e.getY());
        gameContainer.getGrid().play(coordX,coordY);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        /**
         * Interaction with the grid (by moving the cursor)
         */
        PointView hovering = aimedPoint(e.getX(),e.getY());
        PointView hovered = gameContainer.lastHovered();
        if(hovered==null && hovering!=null){
            hovering.setHovered(true);
            hovering.removeAll();
            hovering.paintComponent(hovering.getGraphics());
        } else if(hovered!=null && hovering==null) {
            hovered.setHovered(false);
            hovered.removeAll();
            hovered.paintComponent(hovered.getGraphics());
        } else if(hovered!=hovering) {
            hovered.setHovered(false);
            hovering.setHovered(true);
            hovered.removeAll();
            hovering.removeAll();
            hovered.paintComponent(hovered.getGraphics());
            hovering.paintComponent(hovering.getGraphics());
        }
    }

    /**
     * Returns the point view according to the given coordinates
     *
     * @param x X-coordinate in the grid
     * @param y Y-coordinate in the grid
     * @return
     */
    private PointView aimedPoint(int x, int y){
        return gameContainer.getPointView(getGridX(x),getGridY(y));
    }

    /**
     * Returns the cursor X-coordinate in the grid
     * @param x Graphical X-coordinate (in the JFrame)
     * @return
     */
    private int getGridX(int x){
        return (x-xOffset)/pointSize;
    }

    /**
     * Returns the cursor Y-coordinate in the grid
     * @param y Graphical Y-coordinate (in the JFrame)
     * @return
     */
    private int getGridY(int y){
        return (y-yOffset)/pointSize;
    }
}
