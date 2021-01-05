import model.*;
import view.*;
import controller.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GamePanelTest {

    @Test
    public void testNewGame(){
        GamePanel gamePanel = new GamePanel(16);
        gamePanel.setGridChooser(new GridChooser());
        gamePanel.setModeSlider(new ModeSlider());
        gamePanel.getGrid().play(10,5);
        assertEquals(gamePanel.getGrid().getPoint(10,5).getIndex(),1);
        gamePanel.newGame();
        assertEquals(gamePanel.getGrid().getPoint(10,5).getIndex(),-1);
    }
}
