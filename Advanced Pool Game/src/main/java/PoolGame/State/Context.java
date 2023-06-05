package PoolGame.State;
import PoolGame.Game;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Context of the State design pattern
 */
public class Context {
    private State state;
    /**
     * The Context constructor with default state at easy level
     */
    public Context(){
        state = new EasyLevel();
    }
    public void setState(State st){
        state = st;
    }
    /**
     * The Context of the State design pattern
     * @param stage The application Stage
     * @param root The application root
     * @param easy the button for the easy level
     * @param normal the button for the normal level
     * @param hard the button for the hard level
     * @param timer the timer for the game
     * @param score the score for the game
     * @return the new state of the game
     */
    public Game load(Stage stage, Group root, Button easy, Button normal, Button hard, Text timer, Text score) {

        return state.load(this,stage,root, easy, normal, hard, timer, score);

    }


}
