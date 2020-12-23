package controller;

import model.Point;
import view.GamePanel;
import view.PointView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseMotionListener, MouseListener {
    final int xOffset = 50+9;
    final int yOffset = 50+33;
    private GamePanel gameContainer;

    public MouseController(GamePanel gamePane){
        this.gameContainer = gamePane;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int coordX = getGridX(e.getX());
        int coordY = getGridY(e.getY());
        Point p = chosenPlay(e.getX(),e.getY());
        if(p!=null){
            if(gameContainer.getGrid().playableSpot(coordX,coordY)){
                gameContainer.getGrid().play(coordX,coordY,0,0); // update this line
                gameContainer.getPointView(coordX,coordY).setIndex(gameContainer.getGrid().turn());
                gameContainer.getPointView(coordX,coordY).removeAll();
                gameContainer.getPointView(coordX,coordY).paintComponent(gameContainer.getPointView(coordX,coordY).getGraphics());
            }
        }
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

    public PointView aimedPoint(int x, int y){
        return gameContainer.getPointView(getGridX(x),getGridY(y));
    }

    public int getGridX(int x){
        return (x-xOffset)/25;
    }

    public int getGridY(int y){
        return (y-yOffset)/25;
    }

    public Point chosenPlay(int x, int y){
        return gameContainer.getGrid().getPoint(getGridX(x),getGridY(y));
    }
}
