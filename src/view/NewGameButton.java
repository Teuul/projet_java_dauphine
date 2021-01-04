package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameButton extends JButton {
    private GamePanel gameContainer;            // Game pane
    private GridBagConstraints constraints;     // Button displaying constraints

    public NewGameButton(GamePanel container){
        setPreferredSize(new Dimension(200,170));
        setBackground(Color.DARK_GRAY);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setText("restart");

        this.gameContainer = container;
        this.constraints = new GridBagConstraints();
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        this.constraints.weighty = 1;
        this.constraints.gridwidth = 1;
        this.constraints.gridheight = 1;
        this.constraints.anchor = GridBagConstraints.NORTH;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("NewGame pressed");
                gameContainer.newGame();
            }
        });
    }

    public GridBagConstraints getConstraints(){
        return constraints;
    }
}
