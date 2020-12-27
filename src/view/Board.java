package view;

import controller.MouseController;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{
    private GamePanel gamePane;     // game container (JPanel)
    private MenuPanel menuPane;
    private int size;               // game size

    public Board(int size){
        this.size = size;
        this.gamePane = new GamePanel(size);
        this.menuPane = new MenuPanel(gamePane);

        getContentPane().add(gamePane);
        getContentPane().add(menuPane);

        MouseController mc = new MouseController(gamePane);
        addMouseListener(mc);
        addMouseMotionListener(mc);

        this.setIconImage(new ImageIcon("resources/icon.png").getImage());
    }

    public static void main(String args[]){
        JFrame board = new Board(16);
        board.setLayout(new GridBagLayout());
        board.pack();
        board.setVisible(true);
        board.setResizable(false);
        board.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
