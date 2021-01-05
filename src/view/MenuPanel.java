package view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private NewGameButton restartButton;    // Button: starts a new game
    private SolveButton iaButton;           // Button: execute the AI solving algorithm
    private GamePanel gameContainer;        // Game pane
    private GridChooser gridTextField;      // Selecting grid file text field
    private ModeSlider slider;              // Selecting game mode slider

    public MenuPanel(GamePanel container){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(200,500));
        setBackground(Color.ORANGE);

        this.gameContainer = container;
        this.restartButton = new NewGameButton(gameContainer);
        this.iaButton = new SolveButton(gameContainer.getBot());
        this.gridTextField = new GridChooser();
        this.slider = new ModeSlider();

        this.add(restartButton,restartButton.getConstraints());
        this.add(iaButton,iaButton.getConstraints());
        this.add(gridTextField,gridTextField.getConstraints());
        this.add(slider,slider.getConstraints());
    }

    public GridChooser getGridChooser(){
        return gridTextField;
    }

    public ModeSlider getModeSlider(){ return slider; }
}
