package view;

import model.Line;

public class LineView {
    private Line modelLine;
    private GamePanel container;

    public LineView(Line l,GamePanel container){
        this.modelLine = l;
        this.container = container;
    }
}
