package view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private NewGameButton restartButton;    // Button: starts a new game
    private SolveButton iaButton;           // Button: execute the AI solving algorithm
    private GamePanel gameContainer;        // Game pane

    public MenuPanel(GamePanel container){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(200,500));
        setBackground(Color.ORANGE);

        this.gameContainer = container;
        this.restartButton = new NewGameButton(gameContainer);
        this.iaButton = new SolveButton(gameContainer);

        this.add(restartButton,restartButton.getConstraints());
        this.add(iaButton,iaButton.getConstraints());
    }
}
