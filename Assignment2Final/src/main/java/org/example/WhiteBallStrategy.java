package org.example;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class WhiteBallStrategy implements BallStrategy{

    @Override
    public List<Components> ballIn(boolean in, List<Components> balls, List<List<Double>> pockets,GameWindow w) {
        if(in){
            System.out.println("RESTART");
            JSONConfigReader jcr = new JSONConfigReader();
            Table table = (Table) jcr.tableParse("src/main/resources/config.json");
            balls = jcr.ballParse("src/main/resources/config.json");
            w.setBalls(balls);
            w.setPit(new BallPit(balls, table, pockets, w));


        }


        return balls;
    }
}
