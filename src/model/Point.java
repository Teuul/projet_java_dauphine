package model;

import java.util.ArrayList;

public class Point {
    private int x,y;
    private ArrayList<Line> lines;
    private int index;

    public Point(int x,int y,int index){
        this.x = x;
        this.y = y;
        this.index = index;
        lines = new ArrayList<>();
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

    public int getY() {
        return y;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "("+x+";"+y+") > "+index;
    }
}
