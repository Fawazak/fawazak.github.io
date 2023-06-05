/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package PoolGame;

import PoolGame.Observer.Observer;
import PoolGame.Observer.ObserverImpl;
import javafx.scene.text.Font;
import PoolGame.State.*;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;


/** The JavaFX application */
public class App extends Application {
    ConfigReader config;
    Game game ;
    Group root;
    Scene scene;
    Observer observer;
    private final double FRAMETIME = 1.0 / 60.0;
    Text timer = new Text("0:00");

    Text score;
    void change(Text text, Game game) {

        if(game.secs == 60) {
            game.mins++;
            game.secs = 0;
        }
        text.setText((((game.mins/10) == 0) ? game.mins : "")  + ":"
                + (((game.secs/10) == 0) ? "0" : "")  + game.secs++);
    }

    @Override
    public void start(Stage stage) {

        Text instruction =  new Text(600,200, "Press Start to begin the game \nincluding (timer & score)");
        Button start = new Button("Start");

        root = new Group();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("PoolGame");
        stage.show();
        stage.setWidth(850);
        stage.setHeight(400);

        // Create level buttons
        Button easy = new Button("Easy" );
        Button normal = new Button("Normal");
        Button hard = new Button("Hard");

        root.getChildren().add(easy);
        root.getChildren().add(normal);
        root.getChildren().add(hard);
        root.getChildren().add(start);
        root.getChildren().add(instruction);

        easy.setLayoutX(600);
        easy.setLayoutY(40);

        normal.setLayoutX(600);
        normal.setLayoutY(70);

        hard.setLayoutX(600);
        hard.setLayoutY(100);

        start.setLayoutX(600);
        start.setLayoutY(225);


        Font font = Font.font("Courier New", FontWeight.BOLD, 20);
        timer.setFont(font);
        start.setFont(font);

        //Timer timeline
        Timeline t = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                change(timer, game);
            }
        }));
        t.setCycleCount(Timeline.INDEFINITE);

        // Action event
        Context c = new Context();
        EventHandler<ActionEvent> startEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                root.getChildren().remove(instruction);
                game.removeDrawables(root);
                game = c.load(stage, root, easy,normal,hard, timer, score);

                List<Game> games = new ArrayList<>();
                List<Observer> observers = new ArrayList<>();
                games.add(game);
                observer = new ObserverImpl(game);
                observers.add(observer);
                game.attach(observer);
                //Restart timer with every start press
                game.mins = 0;
                game.secs = 0;
                t.play();

            }
        };
        start.setOnAction(startEvent);

        game = c.load(stage, root, easy,normal,hard, timer, score);


        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(FRAMETIME),
                (actionEvent) -> {
                    //Adds the score ont the screen after getting score from the observer
                    root.getChildren().remove(score);
                    if(observer != null){
                        score = new Text(String.format("Score: %d", observer.getScore()));
                        score.setX(625);
                        score.setY(220);
                        root.getChildren().add(score);
                    }

                    //Reset timer if white ball falls
                    if(game.fell){
                        game.mins = 0;
                        game.secs = 0;
                        game.fell = false;
                    }
                    // Pause timer if the level is changed or the game is won
                    if(game.shownWonText || game.change){
                        t.pause();
                    }
                    game.tick();
                });


        timeline.getKeyFrames().add(frame);
        timeline.play();


    }

    /**
     * The entry point of the program
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
