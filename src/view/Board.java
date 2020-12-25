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

        // TOP GAME PANE BANNER
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

        // LEFT GAME PANE BANNER
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

        // RIGHT GAME PANE BANNER
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

        // BOTTOM GAME PANE BANNER
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
                PointView p = new PointView(getGrid().getPoint(i,j));
                gamePane.add(p,p.getConstraints());
                gamePane.addPointView(i,j,p);
                getGrid().getPoint(i,j).setView(p);
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
        board.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
