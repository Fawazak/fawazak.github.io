package org.example;

public class BallDirector {


    public void construct(BallBuilder builder, String colour, double positionX, double positionY, double velocityX, double velocityY, double mass) {
        builder.setColour(colour);
        builder.setMass(mass);
        builder.setxPosition(positionX);
        builder.setyPosition(positionY);
        builder.setxVelocity(velocityX);
        builder.setyVelocity(velocityY);

    }
}
