package view;

import controller.MouseController;
import model.Grid;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{
    private GamePanel gamePane;
    private int size;
    private Grid grid;

    public Board(int size){
        this.size = size;
        this.grid = new Grid(size,"resources/init_grid.txt", Grid.mode.TOUCHING);
        this.gamePane = new GamePanel(size,grid);

        JPanel topBanner = new JPanel();
        topBanner.setPreferredSize(new Dimension(500,50));
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.weighty = 1;
        cons.gridwidth = size+2;
        cons.gridheight = 1;
        cons.anchor = GridBagConstraints.NORTH;
        gamePane.add(topBanner,cons);

        JPanel leftBanner = new JPanel();
        leftBanner.setPreferredSize(new Dimension(50,400));
        cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 1;
        cons.weighty = size;
        cons.gridwidth = 1;
        cons.gridheight = size;
        cons.anchor = GridBagConstraints.WEST;
        gamePane.add(leftBanner,cons);

        JPanel rightBanner = new JPanel();
        rightBanner.setPreferredSize(new Dimension(50,400));
        cons = new GridBagConstraints();
        cons.gridx = (size+2)-1;
        cons.gridy = 1;
        cons.weighty = size;
        cons.gridwidth = 1;
        cons.gridheight = size;
        cons.anchor = GridBagConstraints.EAST;
        gamePane.add(rightBanner,cons);

        JPanel bottomBanner = new JPanel();
        bottomBanner.setPreferredSize(new Dimension(500,50));
        cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = (size+2)-1;
        cons.weighty = 1;
        cons.gridwidth = size+2;
        cons.gridheight = 1;
        cons.anchor = GridBagConstraints.SOUTH;
        gamePane.add(bottomBanner,cons);

        for(int j=0;j<size;j++){
            for(int i=0;i<size;i++){
                PointView p = new PointView(i,j,getGrid().getPoint(i,j).getIndex());
                cons = new GridBagConstraints();
                cons.gridx = i+1;
                cons.gridy = j+1;
                cons.weightx = 1;
                cons.weighty = 1;
                cons.anchor = GridBagConstraints.NORTHWEST;
                gamePane.add(p,cons);
                gamePane.addPointView(i,j,p);
            }
        }

        getContentPane().add(gamePane);

        MouseController mc = new MouseController(gamePane);
        addMouseListener(mc);
        addMouseMotionListener(mc);
    }

    public Grid getGrid(){
        return grid;
    }

    public static void main(String args[]){
        JFrame board = new Board(16);
        board.pack();
        board.setVisible(true);
        board.setResizable(false);
    }
}
