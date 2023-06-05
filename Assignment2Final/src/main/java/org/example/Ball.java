package org.example;

import java.util.List;

public class Ball implements Components{
    private final String colour;
    private  double positionX;
    private  double positionY;
    private  double velocityX;
    private  double velocityY;
    private  double mass;
    private BallStrategy strategy;
    private final double startXPos;
    public final double startYPos;



    //    public double radius = 15;
    Ball(String colour, double positionX, double positionY,double velocityX,double velocityY, double mass) {
        startXPos =  positionX;
        startYPos = positionY;
        this.colour = colour;
        this.mass = mass;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }
//

    @Override
    public String getColour() {
        return colour;
    }
    public double getStartX() {
        return startXPos;
    }
    public double getStartY() {
        return startYPos;
    }


    @Override
    public double getX() {
        return positionX;
    }

    @Override
    public double getY() {
        return positionY;
    }

    @Override
    public double getFrictionOrMass() {
        return mass;
    }

    @Override
    public void setxVel(double xVel) {
        this.velocityX = xVel;
    }

    public void setyVel(double yVel) {
        this.velocityY = yVel;
    }

    @Override
    public double getxVelocity() {
        return velocityX;
    }

    @Override
    public double getyVelocity() {
        return velocityY;
    }
    public void setxPos(double xPos) {
        this.positionX = xPos;
    }

    public void setyPos(double yPos) {
        this.positionY = yPos;
    }

    public void tick() {
//        for(int i = 0; i<velocityX;i++){
//            positionX += 20;
//
//        }
//        for(int i = 0; i<velocityY;i++){
//            positionY += 20;
//
//        }
        positionX += velocityX;
        positionY += velocityY;
    }
    public List<Components> think(boolean in, List<Components> balls, List<List<Double>> pockets, GameWindow w){
        return strategy.ballIn(in , balls, pockets, w);


    }
    @Override
    public void setStrategy(BallStrategy strategy){
        this.strategy = strategy;

    }


}
