package org.example;


import java.util.List;

public class Table implements Components {
    private final double height;
    private final double width;
    private final String colour;
    private final double friction;
//    private final double start_X_Pos;
//    public final double start_Y_Pos;
    Table(String colour , double friction, double width, double height) {
        this.height = height;
        this.width = width;
        this.colour = colour;

        this.friction = friction;
    }



    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public double getX() {
        return width;
    }

    @Override
    public double getY() {
        return height;
    }

    @Override
    public double getFrictionOrMass() {
        return friction;
    }

    @Override
    public void setxVel(double xVel) {

    }

    @Override
    public void setyVel(double yVel) {

    }

    @Override
    public double getxVelocity() {
        return 0;
    }

    @Override
    public double getyVelocity() {
        return 0;
    }

    @Override
    public void setxPos(double xPos) {

    }

    @Override
    public void setyPos(double yPos) {

    }

    @Override
    public void tick() {

    }

    @Override
    public List<Components> think(boolean in, List<Components> balls, List<List<Double>> pockets, GameWindow w) {

        return balls;
    }

    @Override
    public void setStrategy(BallStrategy strategy) {

    }
    public double getStartX() {
        return 0;
    }
    public double getStartY() {
        return 0;
    }


}
