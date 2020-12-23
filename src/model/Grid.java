package model;

import javax.sound.midi.SysexMessage;
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

    public boolean playableSpot(int x,int y){
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

            System.out.println(x+","+y+":");

            int i;
            // direction 1
            int c = 0;
            for(i=-4;i<=4;i++){
                c = countPoint(x+i,y,x,y,c,bInf,bSup);
                //System.out.println("Hor-> "+c);
                if(c==5)
                    break;
            }
            //System.out.println();
            if(c==5) {
                System.out.println("HOR "+c + ";");
                return true;
            }
            System.out.print("HOR "+c+";");
            // direction 2
            c = 0;
            for(i=-4;i<=4;i++){
                c = countPoint(x,y+i,x,y,c,bInf,bSup);
                //System.out.println("Vert-> "+c);
                if(c==5)
                    break;
            }
            if(c==5) {
                System.out.println("VER "+c + ";");
                return true;
            }
            System.out.print("VER "+c+";");

            // direction 3
            c = 0;
            for(i=-4;i<=4;i++){
                c = countPoint(x+i,y+i,x,y,c,bInf,bSup);
                //System.out.println("D_down-> "+c);
                if(c==5)
                    break;
            }
            if(c==5) {
                System.out.println("DIA_d "+c + ";");
                return true;
            }
            System.out.print("DIA_d "+c+";");

            // direction 4
            c = 0;
            for(i=-4;i<=4;i++){
                c = countPoint(x+i,y-i,x,y,c,bInf,bSup);
                //System.out.println("D_up-> "+c);
                if(c==5)
                    break;
            }
            if(c==5) {
                System.out.println("DIA_u "+c + ";");
                return true;
            }
            System.out.print("DIA_u "+c+";");

        }
        System.out.println("\n");
        return false;
    }

    public void play(int x1, int y1, int x2, int y2){
        /**
         * Updates the model (and view) according to the chosen play
         */
        getPoint(x1,y1).setIndex(lines.size()+1);
        lines.add(new Line(getPoint(x1,y1),getPoint(x2,y2)));
        // update ui
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
        return lines.size()+1;
    }

    public static void main(String args[]){
        Grid b = new Grid(16,"resources/init_grid.txt",mode.TOUCHING);
        System.out.println(b);
        System.out.println(b.playableSpot(10,5));
        System.out.println(b.playableSpot(10,6));
        System.out.println(b.playableSpot(11,5));
        System.out.println(b.playableSpot(12,5));
    }
}
