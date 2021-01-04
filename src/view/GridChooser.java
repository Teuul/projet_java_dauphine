package view;

import javax.swing.*;
import java.awt.*;

public class GridChooser extends JTextField {
    private GridBagConstraints constraints;

    public GridChooser(){
        setPreferredSize(new Dimension(200,30));
        setText("init_grid.txt");
        setHorizontalAlignment(JTextField.CENTER);

        this.constraints = new GridBagConstraints();
        this.constraints.gridx = 0;
        this.constraints.gridy = 1;
        this.constraints.weighty = 1;
        this.constraints.gridwidth = 1;
        this.constraints.gridheight = 1;
        this.constraints.anchor = GridBagConstraints.SOUTH;
    }

    public GridBagConstraints getConstraints(){
        return constraints;
    }
}
