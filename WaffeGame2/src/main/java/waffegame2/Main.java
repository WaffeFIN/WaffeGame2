package waffegame2;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import waffegame2.logic.Game;
import waffegame2.ui.GraphicalUI;
import waffegame2.ui.TextBasedUI;
import waffegame2.ui.UI;

/**
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-02
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        UI ui;
        boolean graphics = true;
        
        if (graphics) {
            ui = new GraphicalUI();
        } else {
            ui = new TextBasedUI();
        }

        Game game = new Game(ui);

        SwingUtilities.invokeLater(ui);
        Thread.sleep(1000);
        game.run();
    }
}
