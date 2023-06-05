package org.example;

public class BallBuilderImpl implements BallBuilder{
    private String colour;
    private  double positionX;
    private  double positionY;
    private  double velocityX;
    private  double velocityY;
    private  double mass;

    public void setxPosition(double positionX){
        this.positionX = positionX;

    }
    public void setyPosition(double positionY){
        this.positionY = positionY;

    }
    public void setxVelocity(double velocityX){
        this.velocityX = velocityX;

    }
    public void setyVelocity(double velocityY){
        this.velocityY = velocityY;

    }
    public void setMass(double mass){
        this.mass = mass;
    }
   public void setColour(String colour){
        this.colour = colour;
    }

    public Components build(){

        return new Ball( colour, positionX, positionY,velocityX,velocityY,mass);
    }
}
