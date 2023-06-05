package org.example;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BallPit {

    private List<Components> balls = new ArrayList<>();
    private long tickCount = 0;
    private Components table;
    private List<List<Double>> pockets;
    private GameWindow w;

    BallPit(List<Components> balls, Components table, List<List<Double>> pockets, GameWindow w){
        this.balls = balls;
        this.pockets = pockets;
        this.w = w;

        this.table = table;
        for (Components ball: balls) {
            if (Objects.equals(ball.getColour(), "blue")) {
                ball.setStrategy(new BlueBallStrategy());
            } else if (Objects.equals(ball.getColour(), "white")) {
                ball.setStrategy(new WhiteBallStrategy());
            } else if (Objects.equals(ball.getColour(), "red")) {
                ball.setStrategy(new RedBallStrategy());
            }
        }

    }
    /* Code copied from Strategy balls Exercise */
    void tick() {
        tickCount++;

        for(Components ball: balls) {
            ball.tick();
            double x_now = ball.getxVelocity();
            double y_now = ball.getyVelocity();

            if(x_now > 0){
                double new_x = x_now - (table.getFrictionOrMass()/10);
                ball.setxVel(new_x);

            } else if (x_now < 0) {
                double new_x = x_now + (table.getFrictionOrMass()/10);
                ball.setxVel(new_x);

            }
            if(y_now > 0){
                double new_y = y_now - (table.getFrictionOrMass()/10);
                ball.setyVel(new_y);

            } else if (y_now < 0) {
                double new_y = y_now + (table.getFrictionOrMass()/10);
                ball.setyVel(new_y);

            }
            if(x_now <= 0.3 && x_now >= -0.3){
                ball.setxVel(0);
                ball.setyVel(0);
            }

            // Handle the edges (balls don't get a choice here)
            if (ball.getX() + 15 > table.getX()) {
                ball.setxPos(table.getX() - 15);
                ball.setxVel(ball.getxVelocity() * -1);
            }
            if (ball.getX() - 15 < 0) {
                ball.setxPos(0 + 15);
                ball.setxVel(ball.getxVelocity() * -1);
            }
            if (ball.getY() + 15 > table.getY()) {
                ball.setyPos(table.getY() - 15);
                ball.setyVel(ball.getyVelocity() * -1);
            }
            if (ball.getY() - 15 < 0) {
                ball.setyPos(0 + 15);
                ball.setyVel(ball.getyVelocity() * -1);
            }


            for(Components ballB: balls) {

                if (checkCollision(ball, ballB)) {

                    handleCollision(ball, ballB);
                }
            }
            for(List<Double> pocket: pockets) {
                this.balls = ball.think(checkIfIn(ball, pocket), balls, pockets, w);
            }


        }
    }
    private boolean checkCollision(Components ballA, Components ballB) {
        if (ballA == ballB) {
            return false;
        }

        return Math.abs(ballA.getX() - ballB.getX()) < 15 + 15 &&
                Math.abs(ballA.getY() - ballB.getY()) < 15 + 15;
    }



    private void handleCollision(Components ballA, Components ballB) {

        //Properties of two colliding balls
        Point2D posA = new Point2D(ballA.getX(), ballA.getY());
        Point2D posB = new Point2D(ballB.getX(), ballB.getY());
        Point2D velA = new Point2D(ballA.getxVelocity(), ballA.getyVelocity());
        Point2D velB = new Point2D(ballB.getxVelocity(), ballB.getyVelocity());

        //calculate the axis of collision
        Point2D collisionVector = posB.subtract(posA);
        collisionVector = collisionVector.normalize();

        //the proportion of each balls velocity along the axis of collision
        double vA = collisionVector.dotProduct(velA);
        double vB = collisionVector.dotProduct(velB);

        //if balls are moving away from each other do nothing
        if (vA <= 0 && vB >= 0) {
            return;
        }


        double mR = ballB.getFrictionOrMass()/ballA.getFrictionOrMass();

        //The velocity of each ball after a collision can be found by solving the quadratic equation
        //given by equating momentum energy and energy before and after the collision and finding the
        //velocities that satisfy this
        //-(mR+1)x^2 2*(mR*vB+vA)x -((mR-1)*vB^2+2*vA*vB)=0
        //first we find the discriminant
        double a = -(mR + 1);
        double b = 2 * (mR * vB + vA);
        double c = -((mR - 1) * vB * vB + 2 * vA * vB);
        double discriminant = Math.sqrt(b * b - 4 * a * c);
        double root = (-b + discriminant)/(2 * a);

        //only one of the roots is the solution, the other pertains to the current velocities
        if (root - vB < 0.01) {
            root = (-b - discriminant)/(2 * a);
        }

        //The resulting changes in velocity for ball A and B
        Point2D deltaVA = collisionVector.multiply(mR * (vB - root));
        Point2D deltaVB = collisionVector.multiply(root - vB);
        ballA.setxVel(ballA.getxVelocity() + deltaVA.getX());
        ballA.setyVel(ballA.getyVelocity() + deltaVA.getY());
        ballB.setxVel(ballB.getxVelocity() + deltaVB.getX());
        ballB.setyVel(ballB.getyVelocity() + deltaVB.getY());
    }
    /* End of copied code */
    private boolean checkIfIn(Components ballA, List<Double> pocket) {


        return Math.abs(ballA.getX() - pocket.get(0)) < 8 + 15 &&
                Math.abs(ballA.getY() - pocket.get(1)) < 8 + 15;
    }

    List<Components> getBalls(){
        return balls;
    }
}
