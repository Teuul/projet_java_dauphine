package view;

import model.AI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolveButton extends JButton {
    private GamePanel gameContainer;            // Game pane
    private GridBagConstraints constraints;     // Button displaying constraints
    private AI bot;

    public SolveButton(AI bot){
        setPreferredSize(new Dimension(200,250));
        setBackground(Color.DARK_GRAY);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setText("solve");

        this.bot = bot;
        //this.gameContainer = container;
        this.constraints = new GridBagConstraints();
        this.constraints.gridx = 0;
        this.constraints.gridy = 3;
        this.constraints.weighty = 1;
        this.constraints.gridwidth = 1;
        this.constraints.gridheight = 1;
        this.constraints.anchor = GridBagConstraints.SOUTH;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("SolveButton pressed");
                bot.solve();
            }
        });
    }

    public GridBagConstraints getConstraints(){
        return constraints;
    }
}
