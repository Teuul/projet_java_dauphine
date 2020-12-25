package model;

import view.PointView;

import java.util.ArrayList;

public class Point {
    private int x,y;
    private ArrayList<Line> lines;
    private int index;
    private PointView view;

    public Point(int x,int y,int index){
        this.x = x;
        this.y = y;
        this.index = index;
        this.lines = new ArrayList<>();
        this.view = null;
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setView(PointView view){
        this.view = view;
    }

    public PointView getView(){
        return this.view;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "("+x+";"+y+") > "+index;
    }
}
