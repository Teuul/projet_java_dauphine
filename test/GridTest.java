import model.*;
import view.*;
import controller.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    public void testInitGrid(){
        Grid g = new Grid(16,"resources/init_grid.txt", Grid.mode.DISJOINT);
        for(int i=0;i<16;i++){
            assertEquals(g.getPoint(i,0).getIndex(),-1);
        }
    }

    @Test
    public void testIsOver(){
        GamePanel pane = new GamePanel(16);
        assertEquals(pane.getGrid().isOver(),false);
        pane.getBot().solve();
        assertEquals(pane.getGrid().isOver(),true);
    }

    @Test
    public void testPlayable(){
        GamePanel pane = new GamePanel(16);
        Line[] l_extract = new Line[1];
        assertEquals(pane.getGrid().playableSpot(10,5,l_extract),true);
        assertEquals(pane.getGrid().playableSpot(5,5,l_extract),true);
    }

    @Test
    public void testPlay(){
        GamePanel pane = new GamePanel(16);
        assertEquals(pane.getGrid().getPoint(10,5).getIndex(),-1);
        pane.getGrid().play(10,5);
        assertEquals(pane.getGrid().getPoint(10,5).getIndex(),1);
        pane.getGrid().play(5,5);
        assertEquals(pane.getGrid().getPoint(5,5).getIndex(),2);
    }
}
