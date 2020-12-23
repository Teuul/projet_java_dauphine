package view;

import model.Grid;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private PointView[][] gridView;
    private Grid grid;
    private int size;

    public GamePanel(int size, Grid grid){
        this.size = size;
        this.gridView = new PointView[size][size];
        this.grid = grid;

        setPreferredSize(new Dimension(500,500));
        setLayout(new GridBagLayout());
        setBackground(Color.ORANGE);
    }

    public PointView getPointView(int x, int y){
        if(0<=x && x<size && 0<=y && y<size)
            return gridView[x][y];
        return null;
    }

    public void addPointView(int x, int y, PointView pv){
        gridView[x][y] = pv;
    }

    public PointView lastHovered(){
        for(int j=0;j<size;j++){
            for(int i=0;i<size;i++){
                if(gridView[i][j].getHovered()){
                    return gridView[i][j];
                }
            }
        }
        return null;
    }

    public Grid getGrid(){
        return grid;
    }
}
