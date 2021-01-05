package model;

import view.LineType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Grid {

    public enum mode {TOUCHING,DISJOINT};

    private Point[][] grid;
    private ArrayList<Line> lines;
    private int size;
    private mode m;

    public Grid(int size, String filename, mode m){
        grid = readGrid(filename);
        lines = new ArrayList<>();
        this.size = size;
        this.m = m;
    }

    /**
     * Reads a grid from a file (including its size)
     * @param   filename  Name of the file
     * @return  Double sized array of Points representing the grid
     */
    public Point[][] readGrid(String filename){
        String data;
        int grid_size;
        try{
            File f = new File(filename);
            Scanner reader = new Scanner(f);
            if(reader.hasNextLine()){
                data = reader.nextLine();

                // initialize the grid size
                grid_size = Integer.parseInt(data);

                Point[][] grid = new Point[grid_size][grid_size];
                int j = 0;
                while(reader.hasNextLine()) {
                    data = reader.nextLine();
                    int i = 0;
                    while (i<grid_size) {
                        char c = data.charAt(i);
                        if(data.charAt(i)=='X'){
                            grid[i][j] = new Point(i,j,-1);
                        }
                        else{
                            grid[i][j] = new Point(i,j,Integer.parseInt(Character.toString(c)));
                        }
                        i++;
                    }
                    j++;
                }
                return grid;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot read file: "+ filename);
            //e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Returns true if the point is playable and false if not (according to the mode and the position)
     * @param x             X-coordinate in the grid
     * @param y             Y-coordinate in the grid
     * @param line_extract  the line to draw if the chosen spot is played
     * @return              boolean about whether the spot is playable
     */
    public boolean playableSpot(int x,int y,Line[] line_extract){
        if(getPoint(x,y).getIndex()==-1){
            int bInf = 0;   // inferior boundary of the aligning points index set
            int bSup;       // superior boundary of the aligning points index set
            if(m==mode.TOUCHING){
                bSup = lines.size();
            }else{
                bSup = 0;
            }

            Point P1=null;
            Point P2=null;
            int i;

            // direction 1
            int c = 0;
            for(i=-4;i<=4;i++){
                c = countPoint(x+i,y,x,y,c,bInf,bSup);
                if(c==1){
                    P1 = getPoint(x+i,y);
                }
                if(c==5) {
                    P2 = getPoint(x+i,y);
                    break;
                }
            }
            if(c==5) {
                Line l = new Line(P1,P2, LineType.HORIZONTAL);
                line_extract[0] = l;
                //System.out.println("HOR "+ c + ";");
                return true;
            }
            //System.out.print("HOR "+ c+";");

            // direction 2
            c = 0;
            for(i=-4;i<=4;i++){
                c = countPoint(x,y+i,x,y,c,bInf,bSup);
                if(c==1){
                    P1 = getPoint(x,y+i);
                }
                if(c==5) {
                    P2 = getPoint(x,y+i);
                    break;
                }
            }
            if(c==5) {
                Line l = new Line(P1,P2,LineType.VERTICAL);
                line_extract[0] = l;
                //System.out.println("VER "+ c + ";");
                return true;
            }
            //System.out.print("VER "+c+";");

            // direction 3
            c = 0;
            for(i=-4;i<=4;i++){
                c = countPoint(x+i,y+i,x,y,c,bInf,bSup);
                if(c==1){
                    P1 = getPoint(x+i,y+i);
                }
                if(c==5) {
                    P2 = getPoint(x+i,y+i);
                    break;
                }
            }
            if(c==5) {
                Line l = new Line(P1,P2,LineType.DIAGONAL_DOWN);
                line_extract[0] = l;
                //System.out.println("DIA_d "+ c + ";");
                return true;
            }
            //System.out.print("DIA_d "+ c +";");

            // direction 4
            c = 0;
            for(i=-4;i<=4;i++){
                c = countPoint(x+i,y-i,x,y,c,bInf,bSup);
                if(c==1){
                    P1 = getPoint(x+i,y-i);
                }
                if(c==5) {
                    P2 = getPoint(x+i,y-i);
                    break;
                }
            }
            if(c==5) {
                Line l = new Line(P1,P2,LineType.DIAGONAL_UP);
                line_extract[0] = l;
                //System.out.println("DIA_u "+ c + ";");
                return true;
            }
            //System.out.print("DIA_u "+ c +";");

        }
        return false;
    }

    /**
     * Updates the model, placing a point
     *
     * @param x     X-coordinate of the chosen play
     * @param y     Y-coordinate of the chosen play
     */
    public void play(int x,int y){
        Point p = getPoint(x,y);
        if(p!=null){
            Line[] line_extract = new Line[1];
            if(playableSpot(x,y,line_extract)){
                Line l = line_extract[0];
                lines.add(l);
                ArrayList<Point> points = getLinePoints(l);
                for(Point point: points){
                    point.setIndex(lines.size());
                    point.getView().updateLineToDraw(l.getType());
                    point.getView().setIndex(lines.size());
                    point.getView().updateView();
                }
                if(isOver()){
                    System.out.println("Game is finished");
                }
            }
        }
    }

    /**
     * Used with playableSpot, to find the maximum number of aligned point (meraeable into one line) around a certain point
     * @param x     X-coordinate of the chosen play
     * @param y     Y-coordinate of the chosen play
     * @param testX X-coordinate of an aligned point within a range of 5
     * @param testY Y-coordinate of an aligned point within a range of 5
     * @param c     counter of aligned point
     * @param bInf  lower boundary of the playable point index set
     * @param bSup  upper boundary of the playable point index set
     * @return      updated counter of aligned point
     */
    private int countPoint(int x,int y,int testX,int testY,int c,int bInf,int bSup){
        if(getPoint(x,y)!=null){
            //System.out.print("("+x+";"+y+") : ");
            if((bInf<=getPoint(x,y).getIndex() && getPoint(x,y).getIndex()<=bSup) || (x==testX && y==testY)){
                return c+1;
            }
            return 0;
        }
        return c;
    }

    /**
     * Returns the chosen point (if exist)
     * @param x X-coordinate in the grid
     * @param y Y-coordinate in the grid
     * @return  Point
     */
    public Point getPoint(int x,int y){
        if(0<=x && x<size && 0<=y && y<size)
            return grid[x][y];
        return null;
    }

    @Override
    public String toString() {
        String res="";
        res+="   ";
        for(int j=0;j<size;j++){
            res+=(j%10+" ");
        }
        res+="\n";
        for(int j=0;j<size;j++){
            res+=(j%10+"  ");
            for(int i=0;i<size;i++){
                if(getPoint(i,j).getIndex()!=-1)
                    res+=getPoint(i,j).getIndex()+" ";
                else
                    res+="X ";
            }
            res+="\n";
        }
        return res;
    }

    /**
     * Returns the points composing the given line
     * @param l Line object
     * @return  an array of Points, composing the given line
     */
    private ArrayList<Point> getLinePoints(Line l){
        ArrayList<Point> points = new ArrayList<>();
        points.add(l.getP1());
        int DX = l.getP2().getX()-l.getP1().getX();
        int DY = l.getP2().getY()- l.getP1().getY();

        for(int i=1;i<=3;i++){
            points.add(getPoint(l.getP1().getX()+(DX/4)*i,l.getP1().getY()+(DY/4)*i));
        }
        points.add(l.getP2());
        return points;
    }

    /**
     * Returns true if the game is over, false if not
     * @return  boolean whether the game is over or not
     */
    public boolean isOver(){
        Line[] l_extract = new Line[1];
        for(int j=0;j<size;j++){
            for(int i=0;i<size;i++){
                if(playableSpot(i,j,l_extract)){
                    return false;
                }
            }
        }

        return true;
    }

    public int getSize(){
        return size;
    }
}
