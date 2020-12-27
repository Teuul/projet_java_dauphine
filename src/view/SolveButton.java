package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolveButton extends JButton {
    private GamePanel gameContainer;            // Game pane
    private GridBagConstraints constraints;     // Button displaying constraints

    public SolveButton(GamePanel container){
        setPreferredSize(new Dimension(200,250));
        setBackground(Color.DARK_GRAY);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setText("solve");

        this.gameContainer = container;
        this.constraints = new GridBagConstraints();
        this.constraints.gridx = 0;
        this.constraints.gridy = 1;
        this.constraints.weighty = 1;
        this.constraints.gridwidth = 1;
        this.constraints.gridheight = 1;
        this.constraints.anchor = GridBagConstraints.SOUTH;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("SolveButton pressed");
            }
        });
    }

    public GridBagConstraints getConstraints(){
        return constraints;
    }
}
