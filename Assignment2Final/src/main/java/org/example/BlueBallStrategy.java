package org.example;

import java.util.List;

public class BlueBallStrategy implements BallStrategy{
    int numberOfTimes = 0;
    @Override
    public List<Components> ballIn(boolean in, List<Components>balls, List<List<Double>> pockets, GameWindow w) {
        if(in){

            for (Components b: balls){
                for(List<Double> p: pockets) {
                    if (b.getColour().equals("blue") && numberOfTimes <= 1 && Math.abs(b.getX() - p.get(0)) < 8 + 15 &&
                            Math.abs(b.getY() - p.get(1)) < 8 + 15) {
                        numberOfTimes++;

                        for(Components c: balls){
                            if(c.getX() == b.getStartX() && c.getY() == c.getStartY()){
                                balls.remove(b);
                                break;
                            }
                        }
                        b.setxPos(b.getStartX());
                        b.setyPos(b.getStartY());
                        b.setxVel(0);
                        b.setyVel(0);

                    } else if (b.getColour().equals("blue") && numberOfTimes > 1) {
                        balls.remove(b);

                    }
                }
            }

        }
        return balls;
    }
}
