package model;

import view.PointView;

import java.util.ArrayList;

public class Point {
    private int x,y;                // X-coordinate and Y-coordinate in the grid (starting at the left top corner, ending at the right bottom corner)
    private int index;              // status of the point (latest line it belongs to)
    private PointView view;         // reference to its model view

    public Point(int x,int y,int index){
        this.x = x;
        this.y = y;
        this.index = index;
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

    @Override
    public String toString() {
        return "("+x+";"+y+") > "+index;
    }
}
