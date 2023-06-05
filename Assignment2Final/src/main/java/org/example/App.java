package org.example;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        JSONConfigReader jcr = new JSONConfigReader();
        Table table = (Table) jcr.tableParse("src/main/resources/config.json");
        List<Components> ball = jcr.ballParse("src/main/resources/config.json");

        //List of pocket positions according to the Table size
        List<List<Double>> pockets = List.of(
                List.of(6.0,6.0), List.of(table.getX()/2, 0.0), List.of(table.getX()-6, 6.0), List.of(6.0, table.getY()-6),
                List.of(table.getX()/2, table.getY()), List.of(table.getX()-6, table.getY()-6));


        GameWindow window = new GameWindow(table, ball, pockets);

        primaryStage.setTitle("Pool Game");
        primaryStage.setScene(window.getScene());
        primaryStage.show();

        window.run();
    }
}
