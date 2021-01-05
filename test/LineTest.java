import model.*;
import view.*;
import controller.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LineTest {

    @Test
    public void testGetCoordinate(){
        GamePanel pane = new GamePanel(16);
        Line[] l_extract = new Line[1];
        pane.getGrid().playableSpot(10,5,l_extract);
        assertEquals(l_extract[0].getP1().getX(),8);
        assertEquals(l_extract[0].getP1().getY(),3);
        assertEquals(l_extract[0].getP2().getX(),12);
        assertEquals(l_extract[0].getP2().getY(),7);
    }
}
