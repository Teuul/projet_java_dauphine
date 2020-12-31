package model;

public class AI {
    private Grid grid;

    public AI(Grid grid){
        this.grid = grid;
    }

    public void solve(){
        while(!grid.isOver()){
            int x = (int)(Math.random()*grid.getSize());
            int y = (int)(Math.random()*grid.getSize());
            System.out.println("ai: "+x+","+y);
            grid.play(x,y);
        }
    }

    public void reset(Grid grid){
        this.grid = grid;
    }
}
