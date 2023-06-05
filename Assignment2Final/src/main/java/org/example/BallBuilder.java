package org.example;

public interface BallBuilder {
    void setxPosition(double xPosition);
    void setyPosition(double yPosition);
    void setxVelocity(double xVelocity);
    void setyVelocity(double yVelocity);
    void setMass(double mass);
    void setColour(String colour);

    Components build();
}
