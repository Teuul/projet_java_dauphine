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

    public Point[][] readGrid(String filename){
        /**
         * Reads a grid from a file (including its size)
         */
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
            e.printStackTrace();
        }
        return null;
    }

    public boolean playableSpot(int x,int y,Line[] line_extract){
        /**
         * Returns true if the point is playable and false if not (according to the mode and the position)
         */
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
        //System.out.println("\n");
        return false;
    }

    public void play(Line l){
        lines.add(l);
        ArrayList<Point> points = getLinePoints(l);
        for(Point p: points){
            p.setIndex(lines.size());
            p.getView().updateLineToDraw(l.getType());
            p.getView().setIndex(lines.size());
            p.getView().updateView();
        }
    }

    public int countPoint(int x,int y,int testX,int testY,int c,int bInf,int bSup){
        /**
         * Used with playableSpot, to find the maximum number of aligned point (meraeable into one line) around a certain point
         */
        if(getPoint(x,y)!=null){
            //System.out.print("("+x+";"+y+") : ");
            if((bInf<=getPoint(x,y).getIndex() && getPoint(x,y).getIndex()<=bSup) || (x==testX && y==testY)){
                return c+1;
            }
            return 0;
        }
        return c;
    }

    public Point getPoint(int x,int y){
        /**
         * Returns the chosen point (if exist)
         */
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

    public int turn(){
        return lines.size();
    }

    public ArrayList<Point> getLinePoints(Line l){
        ArrayList<Point> points = new ArrayList<>();
        points.add(l.getP1());
        int DX = l.getP2().getX()-l.getP1().getX();
        int DY = l.getP2().getY()- l.getP1().getY();
        //System.out.println(DX+" & "+DY);
        for(int i=1;i<=3;i++){
            //System.out.println((l.getP1().getX()+(DX/4)*i)+" | "+(l.getP1().getY()+(DY/4)*i));
            points.add(getPoint(l.getP1().getX()+(DX/4)*i,l.getP1().getY()+(DY/4)*i));
        }
        points.add(l.getP2());
        return points;
    }

    public static void main(String args[]){
        Line[] line_extract = new Line[1];

        Grid b = new Grid(16,"resources/init_grid.txt",mode.TOUCHING);
        System.out.println(b);
        System.out.println(b.playableSpot(10,5,line_extract));
        System.out.println(b.playableSpot(10,6,line_extract));
        System.out.println(b.playableSpot(11,5,line_extract));
        System.out.println(b.playableSpot(12,5,line_extract));
    }
}
