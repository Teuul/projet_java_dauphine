package view;

import model.Grid;
import model.AI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private PointView[][] gridView;     // view grid
    private Grid grid;                  // model grid
    private int size;                   // grid size
    private AI bot;                     // solving model
    private GridChooser gridChooser;    // Grid choosing text field
    private ModeSlider modeSlider;      // Game mode slider (T or D)

    public GamePanel(int size){
        this.size = size;
        this.gridView = new PointView[size][size];
        this.grid = null;
        this.bot = null;

        setPreferredSize(new Dimension(500,500));
        setLayout(new GridBagLayout());
        setBackground(Color.ORANGE);

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
        this.add(topBanner,cons);

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
        this.add(leftBanner,cons);

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
        this.add(rightBanner,cons);

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
        this.add(bottomBanner,cons);

        newGame();
    }

    public PointView getPointView(int x, int y){
        /**
         * Returns the point model view
         */
        if(0<=x && x<size && 0<=y && y<size)
            return gridView[x][y];
        return null;
    }

    public void addPointView(int x, int y, PointView pv){
        /**
         * Add the point model view to the container
         */
        gridView[x][y] = pv;
        gridView[x][y].updateView();
    }

    public PointView lastHovered(){
        /**
         * Search for the last hovered (formerly hovered) point (point view)
         */
        for(int j=0;j<size;j++){
            for(int i=0;i<size;i++){
                if(gridView[i][j].getHovered()){
                    return gridView[i][j];
                }
            }
        }
        return null;
    }

    public void setGridChooser (GridChooser textField){
        this.gridChooser = textField;
    }

    public void setModeSlider (ModeSlider modeSlider) { this.modeSlider = modeSlider; }

    public void newGame(){
        /**
         * Set up the model (grid) and the model view (PointView grid)
         */
        boolean build = true;
        if(grid==null){
            build = false;
        }

        if(!build)
            this.grid = new Grid(size,"resources/init_grid.txt", Grid.mode.TOUCHING);
        else
            this.grid = new Grid(size,"resources/"+gridChooser.getText(),modeSlider.getMode());

        for(int j=0;j<size;j++){
            for(int i=0;i<size;i++){
                if(!build){
                    PointView p = new PointView(getGrid().getPoint(i,j));
                    this.add(p,p.getConstraints());
                    this.addPointView(i,j,p);
                    getGrid().getPoint(i,j).setView(p);
                    this.bot = new AI(this.grid);
                } else {
                    this.getPointView(i,j).resetView(getGrid().getPoint(i,j));
                    getGrid().getPoint(i,j).setView(getPointView(i,j));
                    this.bot.reset(grid);
                }
            }
        }
    }

    public Grid getGrid(){
        return grid;
    }

    public AI getBot(){
        return bot;
    }
}
