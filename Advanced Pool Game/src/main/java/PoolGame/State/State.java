package PoolGame.State;
import PoolGame.Game;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/** State interface */
public interface State {
    /**
     * The Context of the State design pattern
     * @param context The application context
     * @param stage The application Stage
     * @param root The application root
     * @param easy the button for the easy level
     * @param normal the button for the normal level
     * @param hard the button for the hard level
     * @param timer the timer for the game
     * @param score the score for the game
     * @return the new state of the game
     */
    Game load(Context context,  Stage stage, Group root, Button easy, Button normal,  Button hard, Text timer, Text score);
}
