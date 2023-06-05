package org.example;

import java.util.List;
import java.util.Iterator;

public class RedBallStrategy implements BallStrategy{
    @Override
    public List<Components> ballIn(boolean in, List<Components> balls, List<List<Double>> pockets, GameWindow w) {

        if(in){
            for (Components b: balls){
                for(List<Double> p: pockets) {
                    if (b.getColour().equals("red") && Math.abs(b.getX() - p.get(0)) < 8 + 15 &&
                            Math.abs(b.getY() - p.get(1)) < 8 + 15) {
                        balls.remove(b);

                    }
                }
            }

        }
        return balls;
    }
}
