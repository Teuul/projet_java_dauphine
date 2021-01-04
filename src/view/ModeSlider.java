package view;

import model.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class ModeSlider extends JSlider {
    static final int min = 0;
    static final int max = 1;
    static final int init = 0;
    private GridBagConstraints constraints;

    public ModeSlider(){
        super(JSlider.HORIZONTAL,min,max,init);
        setPreferredSize(new Dimension(200,50));
        setMajorTickSpacing(1);
        setPaintTicks(true);
        Hashtable labelTable = new Hashtable();
        labelTable.put(0, new JLabel("T") );
        labelTable.put(1, new JLabel("D") );
        setLabelTable( labelTable );

        setPaintLabels(true);

        this.constraints = new GridBagConstraints();
        this.constraints.gridx = 0;
        this.constraints.gridy = 2;
        this.constraints.weighty = 1;
        this.constraints.gridwidth = 1;
        this.constraints.gridheight = 1;
        this.constraints.anchor = GridBagConstraints.SOUTH;
    }

    public GridBagConstraints getConstraints(){
        return constraints;
    }

    public Grid.mode getMode(){
        if(getValue()==0)
            return Grid.mode.TOUCHING;
        else
            return Grid.mode.DISJOINT;
    }
}
