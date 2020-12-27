package model;

import view.LineType;

public class Line {
    private int index;
    private Point p1,p2;    // the two defining points of the line (segment)
    private LineType type;  // line type (cf. LineType class)

    public Line(Point p1,Point p2,LineType type){
        this.p1=p1;
        this.p2=p2;
        this.type = type;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public LineType getType(){
        return type;
    }
}
