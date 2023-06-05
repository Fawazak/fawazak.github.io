package org.example;

import java.util.List;

public interface Components {


    String getColour();
    double getStartX();
    double getStartY();

    double getX();

    double getY();

    double getFrictionOrMass();

    void setxVel(double xVel);

    void setyVel(double yVel);

    double getxVelocity();

    double getyVelocity();

    void setxPos(double xPos);

    void setyPos(double yPos);
    void tick();
    List<Components> think(boolean x, List<Components> balls, List<List<Double>> pockets, GameWindow w);
    void setStrategy(BallStrategy strategy);
}


